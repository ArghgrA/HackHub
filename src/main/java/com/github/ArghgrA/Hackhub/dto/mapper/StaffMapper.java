package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    @Mapping(source = "hackathon.id", target = "hackathonId")
    StaffDTO toDTO(AbstractStaff staff);
    List<StaffDTO> toDTOList(List<AbstractStaff> staffs);
}
