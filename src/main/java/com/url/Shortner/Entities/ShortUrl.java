package com.url.Shortner.Entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "shorturl")
public class ShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String shortkey;

    @Column(nullable = false)
    private String originalUrl;

    @ManyToOne
    @JoinColumn(name = "createdby")
    private User createdBy;

    @ColumnDefault("false")
    @Column(name = "isPrivate")
    private Boolean isPrivate= false;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "CreatedTime")
    private LocalDateTime createdAt= LocalDateTime.now();

    @Column(name = "expiredAt")
    private LocalDateTime expiredAt;

    @ColumnDefault("0")
    @Column(name = "clickcount")
    private Long clickCount;


}
