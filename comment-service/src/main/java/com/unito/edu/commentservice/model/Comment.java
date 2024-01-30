package com.unito.edu.commentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "craftstore_id", nullable = false)
    private int craftstoreId;

    @Column(name = "craftstore_name", nullable = false)
    private String craftstoreName;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "text", nullable = false)
    private String text;
}
