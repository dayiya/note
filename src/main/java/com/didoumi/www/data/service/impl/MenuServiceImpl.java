package com.didoumi.www.data.service.impl;

import com.didoumi.www.data.dao.MenuDao;
import com.didoumi.www.data.entity.Menu;
import com.didoumi.www.data.service.MenuService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> findMenuByUser(String userId) {
        return menuDao.findMenuByUser(userId);
    }

    @Override
    public List<Menu> selectMenuByMenu(Menu menu) throws Exception {
        if (StringUtils.isEmpty(menu.getUserId()) || StringUtils.isEmpty(menu.getParentMenuId())){
            throw new Exception("Menu's id or parentMenuId can not null");
        }
        return menuDao.selectMenuByMenu(menu);
    }

    @Override
    public void insertMenu(Menu menu) throws Exception {
        if (StringUtils.isEmpty(menu.getUserId())
                || StringUtils.isEmpty(menu.getParentMenuId())
                || StringUtils.isEmpty(menu.getMenuName())
                || StringUtils.isEmpty(menu.getMenuId())
                || StringUtils.isEmpty(menu.getId())) {
            throw new Exception("Incomplete parameters for Menu");
        }
        menuDao.insertMenu(menu);
    }
}
