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
    public List<Menu> findMenuByUser(String userId) {
        return menuDao.findMenuByUser(userId);
    }
}
