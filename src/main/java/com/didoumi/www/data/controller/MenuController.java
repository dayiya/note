package com.didoumi.www.data.controller;

import com.didoumi.www.data.entity.Menu;
import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.MenuService;
import com.didoumi.www.data.utils.Constant;
import com.didoumi.www.data.utils.NoteUtil;
import com.didoumi.www.data.utils.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
            //从session中获取登录账号的信息
            User user = (User)NoteUtil.getSessionForKey(request,"user");
            //获取登录账号user的id，赋值到Menu实体中
            menu.setUserId(user.getUid());
            //从数据库中查询目录实体的List列表，做下拉列表展示
            List<Menu> menuList = menuService.selectMenuByMenu(menu);
            //把查询结果封装到返回的包装类中
            result.setData(menuList);
        } catch (Exception e) {
            result.setCode(Constant.FIVEHANDER.getName());
            result.setErrMsg("method selectMenu error!");
        }
        return result;

    }

    /**
     * 新增目录
     * @param menu
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    @ResponseBody
    public ResultResponse addMenu(Menu menu, HttpServletRequest request) {

        ResultResponse result = new ResultResponse();
        try {
            //设置新增的类型为1,1代表是笔记簿，2代表的是笔记
            menu.setType(Constant.ONE.getName());
            //从session中获取登录账号信息
            User user = (User) NoteUtil.getSessionForKey(request,"user");
            //设置目录与账号关联的id
            menu.setUserId(user.getUid());
            //设置表的主键
            String id = NoteUtil.getId();
            menu.setId(id);
            //目录id，这里是与主键相同
            menu.setMenuId(id);
            //设置目录的创建时间、创建人、默认的更新时间和更新人等
            NoteUtil.setCreteAndUpdateAndTime(menu);
            //插入数据库
            menuService.insertMenu(menu);
            //重新查询目录
            List<Menu> menus = menuService.findMenuByUser(user.getUid());
            //放到session中
            request.getSession().setAttribute(Constant.MENU.getName(), menus);
            result.setData(true);
        } catch (Exception e) {
            result.setData(false);
            result.setCode(Constant.FIVEHANDER.getName());
            result.setErrMsg("method selectMenu error!");
        }
        return result;

    }

}
