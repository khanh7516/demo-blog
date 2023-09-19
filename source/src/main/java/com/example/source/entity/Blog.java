package com.example.source.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tbl_blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String slug;
    private String description;
    private String content;

    private String thumnail;

    private LocalDateTime created_at;
    private LocalDateTime published_at;
    private LocalDateTime updated_at;

    private String status;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToMany
    @JoinTable(
            name="tbl_blog_category",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();




    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }

}
