package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.message.ticket.DefaultTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository<T extends DefaultTicket> extends JpaRepository<T, UUID> {
    @Query("SELECT t FROM DefaultTicket t WHERE t.receiver.id = ?1")
    List<T> findByHackathon(UUID hackathon_id);
}
