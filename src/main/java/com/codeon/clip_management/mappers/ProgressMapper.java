package com.codeon.clip_management.mappers;

import com.codeon.clip_management.dtos.ProgressDTO;
import com.codeon.clip_management.dtos.SetLabel;
import com.codeon.clip_management.models.Progress;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgressMapper {
    ProgressDTO toDTO(Progress progress);
    SetLabel setToDTO(Progress progress);
    List<ProgressDTO> toDTOList(List<Progress> progressList);
    Progress toEntity(ProgressDTO progressDTO);

}
