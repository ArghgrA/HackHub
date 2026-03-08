package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface EvaluationRepository<T extends DefaultEvaluation> extends JpaRepository<T, UUID> {
    @Query("SELECT s FROM DefaultEvaluation s WHERE s.submission.id = ?1")
    Optional<T> findBySubmission(UUID submission_id);
}
