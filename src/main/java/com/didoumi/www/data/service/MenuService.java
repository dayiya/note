package com.didoumi.www.data.service;

import com.didoumi.www.data.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findFirstMenuByUser(String userId);
    List<Menu> findThirdMenuByUser(String userId);
    List<Menu> findSecondMenuByUser(String userId);
}
