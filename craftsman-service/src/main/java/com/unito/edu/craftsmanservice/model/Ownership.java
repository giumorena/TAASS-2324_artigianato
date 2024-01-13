package com.unito.edu.craftsmanservice.model;

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

    @Column(name = "craftstore_id", nullable = false)
    private int craftstoreId;

    @Column(name = "craftstore_name", nullable = false)
    private String craftstoreName;
}
