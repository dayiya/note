package com.didoumi.www.data.service.impl;

import com.didoumi.www.data.dao.ShiroDao;
import com.didoumi.www.data.entity.User;
import com.didoumi.www.data.service.ShiroService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;

@Service
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private ShiroDao shiroDao;

    @Override
    public User findByUsername(String username) {
        if (!StringUtils.isEmpty(username)) {
            return shiroDao.findByUsername(username);
        }
        return new User();
    }
}
