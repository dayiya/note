package com.didoumi.www.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @Column(name = "rname")
    private String rname;

    @Transient
    private Set<Permission> permissions = new HashSet<>();
}
