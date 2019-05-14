package com.weisen.www.code.keybox.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.weisen.www.code.keybox.domain.Keyboxes;
import com.weisen.www.code.keybox.service.dto.Rewrite_001_KeyboxesDTO;

/**
 * Mapper for the entity Keyboxes and its DTO KeyboxesDTO.
 */
@Mapper(componentModel = "spring", uses = {UsertokenMapper.class})
public interface Rewrite_001_KeyboxesMapper extends EntityMapper<Rewrite_001_KeyboxesDTO, Keyboxes> {

    @Mapping(source = "usertoken.id", target = "usertokenId")
    Rewrite_001_KeyboxesDTO toDto(Keyboxes keyboxes);

    @Mapping(source = "usertokenId", target = "usertoken")
    Keyboxes toEntity(Rewrite_001_KeyboxesDTO keyboxesDTO);

    default Keyboxes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Keyboxes keyboxes = new Keyboxes();
        keyboxes.setId(id);
        return keyboxes;
    }
}
