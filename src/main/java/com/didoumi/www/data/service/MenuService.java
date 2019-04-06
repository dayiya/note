package com.didoumi.www.data.service;

import com.didoumi.www.data.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenuByUser(String userId);
}
