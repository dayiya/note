package com.didoumi.www.data.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "menuId")
    private String menuId;

    @Column(name = "parentMenuId")
    private String parentMenuId;

    @Column(name = "menuName")
    private String menuName;

    @Column(name = "type")
    private String type;

    @Column(name = "userId")
    private String userId;

    @Column(name = "createUser")
    private String createUser;

    @Column(name = "updateUser")
    private String updateUser;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "updateTime")
    private Date updateTime;

    @Transient
    private List<Menu> nextMenu;
}
