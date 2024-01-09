package com.unito.edu.craftstoreservice.model.dto;

import com.unito.edu.craftstoreservice.model.Craftstore;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CraftstoreMapper {
    CraftstoreDto toDto(Craftstore craftstore);

    List<CraftstoreDto> toDtoList (List<Craftstore> craftstoreList);
}
