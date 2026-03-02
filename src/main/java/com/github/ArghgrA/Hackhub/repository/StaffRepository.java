package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffRepository<T extends AbstractStaff> extends JpaRepository<T, UUID> {
}
