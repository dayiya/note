package com.didoumi.www.data.dao;

import com.didoumi.www.data.entity.Menu;

import java.util.List;

public interface MenuDao {
    List<Menu> findMenuByUser(String userId);
}
