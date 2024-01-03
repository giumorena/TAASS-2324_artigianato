package com.unito.edu.craftstoreservice.model.dto;

import com.unito.edu.craftstoreservice.model.Ownership;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CraftstoreDto {
    private int id;
    private String name;
    private String category;
    private String description;
    private List<Ownership> ownerList;

    public CraftstoreDto(int id, String name, String category, String description, List<Ownership> ownerList) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.ownerList = ownerList;
    }
}
