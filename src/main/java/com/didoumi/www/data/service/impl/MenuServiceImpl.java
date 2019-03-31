package com.didoumi.www.data.service.impl;

import com.didoumi.www.data.dao.MenuDao;
import com.didoumi.www.data.entity.Menu;
import com.didoumi.www.data.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> findFirstMenuByUser(String userId) {
        return menuDao.findFirstMenuByUser(userId);
    }

    @Override
    public List<Menu> findThirdMenuByUser(String userId) {
        return menuDao.findThirdMenuByUser(userId);
    }

    @Override
    public List<Menu> findSecondMenuByUser(String userId) {
        return menuDao.findSecondMenuByUser(userId);
    }
}
