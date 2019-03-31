package com.didoumi.www.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;
}
