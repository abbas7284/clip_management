package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.SetProgressDTO;
import com.codeon.clip_management.dtos.UsersProgressDTO1;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsersProgressMapper1 {

    @Mapping(source = "progress", target = "progress")
    UsersProgressDTO1 toDto(Users users);
    List<UsersProgressDTO1> toDtoList(List<Users> users);

    UsersProgressDTO1.progress progressToProgressDto(Progress progress);

    @Mapping(target = "progressId", ignore = true)
    @Mapping(target = "sumLabel", expression = "java(0L)")
    Progress toEntity(SetProgressDTO setProgressDTO, Users users);

}
