/**
 * @version V1.0
 * @Title:
 * @Package cn.gyyx.app.webservice
 * @Description: Description ......
 * @author: xin
 * @date: 2018/9/4 11:20
 */
package cn.gyyx.elves.console.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public ModelAndView index(String token, String userName, HttpServletResponse response) throws Exception {
        logger.info("Welcome to Cross Manage System!");
        return new ModelAndView("index");
    }
}
