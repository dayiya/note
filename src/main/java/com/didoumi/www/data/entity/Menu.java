package com.didoumi.www.data.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "menu")
@Data
public class Menu extends CommonFiled {

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

    @Transient
    private List<Menu> nextMenu;
}
