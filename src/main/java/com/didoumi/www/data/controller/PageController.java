package com.didoumi.www.data.controller;

import com.didoumi.www.data.entity.Menu;
import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private MenuService menuService;

    @RequestMapping("login")
    public String login() {
        return "login";
    }
    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout()
    {
        // 先验证主体
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("unauthorized")
    public String unauthorized()
    {
        return "unauthorized";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public void loginUser(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpServletRequest request, HttpSession session) {
        // 初始化这个用户的token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 获取事件的主体
        Subject subject = SecurityUtils.getSubject();
        try
        {
            // 尝试登录
            subject.login(token);
            // 获取用户的全部信息
            User user = (User) subject.getPrincipal();
            List<Menu> menus = menuService.findMenuByUser(user.getUid());

            // 用于界面输出
            session.setAttribute("user", user);
            session.setAttribute("menus", menus);
            //return "redirect:/index";
        }
        catch (Exception e)
        {
            //return "login";
        }
    }

}
