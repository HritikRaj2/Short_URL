package com.url.Shortner.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}