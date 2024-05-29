package com.unito.edu.userservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    private int id;

    @Column(name = "craftstore_id", nullable = false)
    private int craftstoreId;

    @Column(name = "craftstore_name", nullable = false)
    private String craftstoreName;

    @Column(name = "post_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate postDate;

    @Column(name = "text", nullable = false)
    private String text;
}
