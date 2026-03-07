package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.response.AddStaffResponseDTO;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddStaffMapper {
    AddStaffResponseDTO toResponse(AbstractStaff staff);
}
