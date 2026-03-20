package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository<T extends DefaultSubmission> extends JpaRepository<T, UUID> {
    @Query("SELECT s FROM DefaultSubmission s WHERE s.sender.id = ?1")
    Optional<T> findByTeam(UUID team_id);

    @Query("SELECT d FROM DefaultSubmission d WHERE d.receiver.id = ?1")
    List<T> findByHackathon(UUID hackathon_id);
}
