package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.ProjectDTO;
import com.codeon.clip_management.dtos.UsersDTO;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDTO toDTO(Users users);
    List<UsersDTO> toDTOList(List<Users> usersList);
    Users toEntity(UsersDTO usersDTO);
    List<Users> toEntityList(List<UsersDTO> usersDTOList);
}
