package cn.gyyx.elves.console.controller;

import cn.gyyx.elves.console.domain.Result;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.enums.ResultEnum;
import cn.gyyx.elves.console.service.ManageService;
import cn.gyyx.elves.console.utils.ResultUtil;
import cn.gyyx.elves.core.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("/")
@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    public static final int COOKIE_TIME = 60*60*24;

    @Resource
    private ManageService manageService;

    @ResponseBody
    @RequestMapping("/login")
    public Result<Object> login(HttpServletRequest request, HttpServletResponse response,User user)throws Exception{
        user.setPassword( MD5Utils.MD5(user.getPassword()));
        User userAuth = manageService.valUserCenter(user);

        if (null != userAuth) {
            userAuth.setLoginTimes(userAuth.getLoginTimes()+1);
            userAuth.setLastLoginIp(request.getRemoteAddr());
            manageService.reCordUserLogin(userAuth);
            HttpSession session = request.getSession();

            session.setAttribute("curUser", userAuth);
            session.setAttribute("username", userAuth.getUsername());

            setCookie(response, "JSESSIONID", session.getId(),"/", COOKIE_TIME);
            setCookie(response, "name", userAuth.getUsername(),"/", COOKIE_TIME);
            setCookie(response, "email", userAuth.getEmail() + "","/", COOKIE_TIME);
            return ResultUtil.success();
        }else{
            return ResultUtil.error( ResultEnum.ERROR_USER_INFO_ERROR);
        }
    }

    @RequestMapping("/loginOut")
    public void loginOut(HttpServletRequest request){
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("email");
        request.getSession().invalidate();
    }


    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        try {
            cookie.setValue( URLEncoder.encode(value, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.addCookie(cookie);
    }

}
