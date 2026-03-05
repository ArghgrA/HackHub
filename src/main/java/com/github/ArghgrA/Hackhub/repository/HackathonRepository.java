package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface HackathonRepository<T extends AbstractHackathon> extends JpaRepository<T, UUID> {
    @Query("SELECT h FROM AbstractHackathon h JOIN AbstractStaff s ON h.id = s.hackathon.id WHERE s.id = ?1")
    Optional<T> findHackathonByStaff(UUID staff_id);
}
