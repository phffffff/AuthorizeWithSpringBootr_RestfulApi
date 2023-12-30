package com.example.authorizerestfulapi.entity;


import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "role_name", nullable = false)

    @Enumerated(EnumType.STRING)
    private ERole role;
    @Column(name = "status")
    private boolean status;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Users> users = new HashSet<>();
}
