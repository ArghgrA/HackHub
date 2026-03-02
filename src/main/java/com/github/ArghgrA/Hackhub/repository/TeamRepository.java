package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository<T extends AbstractTeam> extends JpaRepository<T, UUID> {
}
