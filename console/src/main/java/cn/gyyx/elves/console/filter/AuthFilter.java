package cn.gyyx.elves.console.filter;

import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.enums.ResultEnum;
import cn.gyyx.elves.console.utils.ResultUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 
 * 跨域过滤器
 */
@Component
public class AuthFilter implements Filter {

	private final static Logger logger = LoggerFactory.getLogger(AuthFilter.class);
	public static ThreadLocal<Object> THREADLOCAL = new ThreadLocal<Object>();

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		// 设置跨域
		String origin = StringUtils.isNotEmpty(request.getHeader("origin")) ? request.getHeader("origin") :request.getHeader("referer");
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Authorization, X-Requested-With, Content-Type, Accept,USER_INFO");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		// 过滤OPTIONS方法
		if("OPTIONS".equals(request.getMethod())){
			filterChain.doFilter(req, res);
			return;
		}

		String requestURI = request.getRequestURI();
		logger.debug("filt uri : " + requestURI);


		//requestURI.equals("/ftp/fileUpload")
		requestURI = requestURI.replaceAll("/+", "/");
		if (	requestURI.equals("/") ||
				requestURI.startsWith("/static")||
				requestURI.startsWith("/resources") ||
				requestURI.equals("/login") ||
				requestURI.equals("/loginOut")
				) {
			filterChain.doFilter(request, response);
			return;
		}


		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("curUser");

		if(null == user){
			//throw new AppException( ResultEnum.ERROR_NO_LOGIN );
			Result<Object> result = ResultUtil.error( ResultEnum.ERROR_NO_LOGIN );
			String jsonStr =  JSONObject.toJSONString( result );

			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(jsonStr );
			return;
		}else{
			THREADLOCAL.set( user );
			filterChain.doFilter(request, response);
			return;
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}