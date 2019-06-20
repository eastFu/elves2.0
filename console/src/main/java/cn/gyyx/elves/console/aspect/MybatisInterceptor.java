package cn.gyyx.elves.console.aspect;

import cn.gyyx.elves.console.domain.Page;
import cn.gyyx.elves.console.utils.ReflectHelper;
import cn.gyyx.elves.console.utils.Reflections;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.PropertyException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

@Service
@SuppressWarnings("restriction")

@Intercepts({																			   //3.4.0之后分页加上  Integer.class important
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class} ),  
}) 
public class MybatisInterceptor implements Interceptor {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static String dialect = "mysql";  	  // 数据库方言
	private static String pageSqlId = ""; 		 // mapper.xml中需要拦截的ID(正则匹配)


	public Object intercept(Invocation ivk) throws Throwable {
		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
					.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

			if (mappedStatement.getId().matches(pageSqlId)) { 	 // 拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject(); // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject尚未实例化！");
				} else {

					Page page = null;
					if (parameterObject instanceof Page) {  // 参数就是Page实体
						page = (Page) parameterObject;
					} else if(parameterObject instanceof Map){ //参数是map
						Map<String,Object> map = (Map)parameterObject;
						Object pageTemp =  map.get("page");

						if(pageTemp != null && pageTemp instanceof Map){
							page = new Page();
							Map<String,Object> mapPageTemp = (Map)pageTemp;
							int currentPage = Integer.parseInt(mapPageTemp.get("currentPage").toString());
							int pageSize = Integer.parseInt(mapPageTemp.get("pageSize").toString());
							page.setCurrentPage(currentPage);
							page.setPageSize(pageSize);

							map.put("page",page);
						}else{
							return ivk.proceed();
						}


					}else{// 参数为某个实体，该实体拥有Pages属性
						Field pageField = ReflectHelper.getFieldByFieldName(parameterObject, "page");
						if (pageField != null) {
							page = (Page) Reflections.invokeGetter(parameterObject, "page");
							if (page == null) {
								return ivk.proceed();
							}
						} else {
							// 不存在page属性
							return ivk.proceed();
						}
					}
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					String countSql = "select count(0) from (" + sql + ") tmp_count";  // 记录统计
					PreparedStatement countStmt = connection .prepareStatement(countSql);
					ReflectHelper.setValueByFieldName(boundSql, "sql", countSql);
					DefaultParameterHandler ParameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
					ParameterHandler.setParameters(countStmt);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					page.setTotalRows(count);
					String pageSql = generatePagesSql(sql, page);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
				}
			}
		}
		return ivk.proceed();
	}


	/**
	 * 根据数据库方言，生成特定的分页sql
	 * 
	 * @param sql
	 * @param page
	 * @return
	 */
	private String generatePagesSql(String sql, Page page) {
		if (page != null && StringUtils.isNotEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect)) {
				pageSql.append(sql);
				pageSql.append(" limit " + (page.getCurrentPage() - 1) * page.getPageSize() + "," + page.getPageSize());
			} else if ("oracle".equals(dialect)) {
				pageSql.append("select * from (select tmp_tb.*,ROWNUM row_id from (");
				pageSql.append(sql);
				pageSql.append(") tmp_tb where ROWNUM<=");
				pageSql.append(page.getCurrentPage() + page.getPageSize());
				pageSql.append(") where row_id>");
				pageSql.append((page.getCurrentPage()-1)*page.getPageSize());
			}
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (StringUtils.isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (StringUtils.isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {
				e.printStackTrace();
			}
		}
	}
}
