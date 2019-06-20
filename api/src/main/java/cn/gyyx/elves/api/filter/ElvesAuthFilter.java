package cn.gyyx.elves.api.filter;

import cn.gyyx.elves.core.config.ElvesConfig;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author East.F
 * @ClassName: ElvesAuthFilter
 * @Description: TODO
 * @date 2019/4/29 18:01
 */
@Order(1)
@WebFilter(filterName = "elvesAuthFilter", urlPatterns = "/api/*")
public class ElvesAuthFilter implements Filter {

    @Resource
    private ElvesConfig elvesConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if(!elvesConfig.isSupervisorEnable()){
            filterChain.doFilter(request,response);
            return;
        }
        //supervisor权限鉴定
        boolean flag = true;
        if(!flag){
            Writer writer = response.getWriter();
            writer.write("fail");
            writer.close();
        }
    }
}
