package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EvaluationRepository<T extends DefaultEvaluation> extends JpaRepository<T, UUID> {
    @Query("SELECT s FROM DefaultEvaluation s WHERE s.submission.id = ?1")
    Optional<T> findBySubmission(UUID submission_id);

    @Query("SELECT s FROM DefaultEvaluation s WHERE s.receiver.id = ?1")
    List<T> findByHackathon(UUID hackathon_id);
}
