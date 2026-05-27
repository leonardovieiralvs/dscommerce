package com.lsouzadev.dscommerce.mapper;

import com.lsouzadev.dscommerce.dto.UserDto;
import com.lsouzadev.dscommerce.entities.Role;
import com.lsouzadev.dscommerce.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "order", ignore = true)
    User toEntity(UserDto userDto);

    default String map(Role role) {
        return role.getAuthority();
    }

    default Role map(String authority) {
        return new Role(null, authority);
    }
}