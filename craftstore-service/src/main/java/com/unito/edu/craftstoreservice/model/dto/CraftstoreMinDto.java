package com.unito.edu.craftstoreservice.model.dto;

import com.unito.edu.craftstoreservice.model.Ownership;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CraftstoreMinDto {
    private int id;
    private String name;
    private String category;
    private String description;
    private List<Ownership> ownerList;

}
