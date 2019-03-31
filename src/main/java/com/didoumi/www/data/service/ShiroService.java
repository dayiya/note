package com.didoumi.www.data.service;

import com.didoumi.www.data.entity.User;

public interface ShiroService {
    User findByUsername(String username);
}
