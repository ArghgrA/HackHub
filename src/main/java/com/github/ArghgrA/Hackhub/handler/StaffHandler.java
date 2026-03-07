package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.AddStaffMapper;
import com.github.ArghgrA.Hackhub.dto.request.AddStaffRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddStaffResponseDTO;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.users.AbstractUser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffHandler {

    private final UserRepository<DefaultUser> userRepository;
    private final UserRepository<AbstractStaff> staffRepository;
    private final AddStaffMapper staffMapper;

    public AddStaffResponseDTO createStaff(AddStaffRequestDTO dto){
        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that id"));

        AbstractStaff newStaff = user.transform(dto.role().getInstance().getClass());
        userRepository.delete(user);

        staffRepository.save(newStaff);

        return staffMapper.toResponse(newStaff);
    }
}
