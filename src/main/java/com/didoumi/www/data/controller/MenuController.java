package com.didoumi.www.data.controller;

import com.didoumi.www.data.entity.Menu;
import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.MenuService;
import com.didoumi.www.data.utils.NoteUtil;
import com.didoumi.www.data.utils.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询当前用户的munu，做select的下拉列表展示用
     * @param menu
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    @ResponseBody
    public ResultResponse selectMemu(Menu menu, HttpServletRequest request) {
        ResultResponse result = new ResultResponse();
        try {
            User user = (User)NoteUtil.getSessionForKey(request,"user");
            menu.setUserId(user.getUid());
            List<Menu> menuList = menuService.selectMenuByMenu(menu);
            result.setData(menuList);
        } catch (Exception e) {
            result.setCode("500");
            result.setErrMsg("method selectMenu error!");
        }
        return result;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse addMenu(Menu menu, HttpServletRequest request) {
        ResultResponse result = new ResultResponse();
        try {
            menu.setType("1");
            User user = (User) NoteUtil.getSessionForKey(request,"user");
            menu.setUserId(user.getUid());
            String id = NoteUtil.getId();
            menu.setId(id);
            menu.setMenuId(id);
            menuService.insertMenu(menu);
            List<Menu> menus = menuService.findMenuByUser(user.getUid());
            request.getSession().setAttribute("menus", menus);
            result.setData(true);
        } catch (Exception e) {
            result.setData(false);
            result.setCode("500");
            result.setErrMsg("method selectMenu error!");
        }
        return result;
    }
}
