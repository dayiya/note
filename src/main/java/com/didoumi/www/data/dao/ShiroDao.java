package com.didoumi.www.data.dao;

import com.didoumi.www.data.entity.User;

public interface ShiroDao {
    User findByUsername(String username);
}
