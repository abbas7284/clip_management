package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.UsersProgressDTO;
import com.codeon.clip_management.models.Progress;
import com.codeon.clip_management.models.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersProgressMapper {
    UsersProgressDTO toDto(Users users, Progress progress);
}
