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
@Table(name = "tbl_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    private String type;

    private LocalDateTime created_at;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
    }
}
