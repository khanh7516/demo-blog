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
    @Column(length = 100, nullable = false)
    private String title;
    @Column(nullable = false)
    private String slug;
    private String description;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String thumbnail;

    private LocalDateTime created_at;
    private LocalDateTime published_at;
    private LocalDateTime updated_at;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private BlogStatus status;

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




