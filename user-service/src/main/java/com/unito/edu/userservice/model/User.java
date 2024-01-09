package com.unito.edu.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    /* Unidirectional One-To-Many relationship.
     *  A user can post multiple comments,
     *  while a comment may have been posted by only one user.
     *  A foreign key user_id will be added to Comment table */
    @OneToMany (fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="user_id")
    private List<Comment> commentList;
}
