package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffDTO toDTO(AbstractStaff staff);
    List<StaffDTO> toDTOList(List<AbstractStaff> staffs);
}
