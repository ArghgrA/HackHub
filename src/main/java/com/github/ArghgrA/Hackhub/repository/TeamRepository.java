package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository<T extends AbstractTeam> extends JpaRepository<T, UUID> {
    @Query("SELECT COUNT(t) > 0 FROM AbstractTeam t JOIN t.hackathons h WHERE t.id = ?1 AND h.id = ?2")
    boolean isParticipating(UUID team_id, UUID hackathon_id);
}
