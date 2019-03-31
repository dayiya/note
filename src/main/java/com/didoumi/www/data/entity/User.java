package com.didoumi.www.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uid;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Transient
    private Set<Role> roles = new HashSet<>();
}
