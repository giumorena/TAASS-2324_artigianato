package com.unito.edu.craftstoreservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ownership")
public class Ownership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "craftsman_id", nullable = false)
    private int craftsmanId;

    @Column(name = "craftsman_name", nullable = false)
    private String craftsmanName;
}
