package com.unito.edu.craftsmanservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "craftsman")
public class Craftsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    /*
     * Used to identify a craftsman after he has logged in
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /* Unidirectional One-To-Many relationship.
     *  A craftsman may have many ownerships (and then craftstores),
     *  while an ownership (not craftstore!) refers only to one craftsman.
     *  A foreign key craftsman_id will be added to Ownership table */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="craftsman_id")
    private List<Ownership> craftstoreList;

}
