package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.invites.AbstractInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InviteRepository<T extends AbstractInvite> extends JpaRepository<T, UUID> {
    @Query("SELECT i FROM AbstractInvite i WHERE i.user.id = ?1 AND i.team.id = ?2")
    Optional<T> findInviteByTeam(UUID user_id, UUID team_id);
}
