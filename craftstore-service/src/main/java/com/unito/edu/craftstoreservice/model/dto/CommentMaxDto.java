package com.unito.edu.craftstoreservice.model.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentMaxDto implements Serializable {

    private int id;
    private int craftstoreId;
    private String craftstoreName;
    private int userId;
    private String userName;
    private LocalDate postDate;
    private String text;
}
