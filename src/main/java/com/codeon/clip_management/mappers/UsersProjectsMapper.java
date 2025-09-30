package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.UsersProjectsDTO;
import com.codeon.clip_management.models.Projects;
import com.codeon.clip_management.models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsersProjectsMapper {
    @Mapping(source = "projects", target = "videoNames")
    UsersProjectsDTO toDto(Users users);
    List<UsersProjectsDTO> toDtoList(List<Users> users);

    default List<String> mapProjectsToVids(List<Projects> projects){
        if (projects == null){
            return null;
        }
        return projects.stream()
                .map(Projects::getVideo)
                .collect(Collectors.toList());
    }

    @Mapping(source = "videoId", target = "id")   // فیلد videoId را به id مپ می‌کند
    @Mapping(source = "video", target = "name")   // فیلد video را به name مپ می‌کند
    UsersProjectsDTO.VideoDetail projectToVideoDetail(Projects project);

    @Mapping(target = "complete", constant = "0")
    @Mapping(target = "editing", constant = "0")
    @Mapping(target = "videoId", ignore = true)
    Projects toEntity(String video, Users users);
}
