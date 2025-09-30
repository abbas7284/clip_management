package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.ProjectDTO;
import com.codeon.clip_management.models.Projects;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDTO(Projects projects);
    List<ProjectDTO> toDTOList(List<Projects> projectsList);
}
