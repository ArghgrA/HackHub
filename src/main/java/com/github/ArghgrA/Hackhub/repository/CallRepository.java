package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CallRepository<T extends DefaultCall> extends JpaRepository<T, UUID>{
    @Query("SELECT c FROM DefaultCall c WHERE c.receiver.id = ?1 AND c.ticket.receiver.id = ?2")
    List<T> findByTeamAndHackathon(UUID teamId, UUID hackathonId);
}
