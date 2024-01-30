package com.unito.edu.craftstoreservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

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

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "text", nullable = false)
    private String text;
}
