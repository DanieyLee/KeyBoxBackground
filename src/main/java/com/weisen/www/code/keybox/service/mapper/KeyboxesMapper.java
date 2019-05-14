package com.weisen.www.code.keybox.service.mapper;

import com.weisen.www.code.keybox.domain.*;
import com.weisen.www.code.keybox.service.dto.KeyboxesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Keyboxes and its DTO KeyboxesDTO.
 */
@Mapper(componentModel = "spring", uses = {UsertokenMapper.class})
public interface KeyboxesMapper extends EntityMapper<KeyboxesDTO, Keyboxes> {

    @Mapping(source = "usertoken.id", target = "usertokenId")
    KeyboxesDTO toDto(Keyboxes keyboxes);

    @Mapping(source = "usertokenId", target = "usertoken")
    Keyboxes toEntity(KeyboxesDTO keyboxesDTO);

    default Keyboxes fromId(Long id) {
        if (id == null) {
            return null;
        }
        Keyboxes keyboxes = new Keyboxes();
        keyboxes.setId(id);
        return keyboxes;
    }
}
