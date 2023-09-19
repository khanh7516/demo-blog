package com.example.source.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(name="blog_id")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }


    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }
}
