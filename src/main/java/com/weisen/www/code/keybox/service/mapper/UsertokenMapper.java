package com.weisen.www.code.keybox.service.mapper;

import com.weisen.www.code.keybox.domain.*;
import com.weisen.www.code.keybox.service.dto.UsertokenDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Usertoken and its DTO UsertokenDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UsertokenMapper extends EntityMapper<UsertokenDTO, Usertoken> {


    @Mapping(target = "userids", ignore = true)
    Usertoken toEntity(UsertokenDTO usertokenDTO);

    default Usertoken fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usertoken usertoken = new Usertoken();
        usertoken.setId(id);
        return usertoken;
    }
}
