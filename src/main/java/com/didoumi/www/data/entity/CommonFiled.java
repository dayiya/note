package com.didoumi.www.data.entity;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public abstract class CommonFiled implements Serializable {

    @Column(name = "createUser")
    private String createUser;

    @Column(name = "updateUser")
    private String updateUser;

    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "updateTime")
    private LocalDateTime updateTime;
}
