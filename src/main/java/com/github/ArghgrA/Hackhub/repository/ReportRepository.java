package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.DefaultReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository<T extends DefaultReport> extends JpaRepository<T, UUID> {
    @Query("SELECT d FROM DefaultReport d WHERE d.receiver.id = ?1")
    List<T> findByHackathon(UUID hackathon_id);
}
