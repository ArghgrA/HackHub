package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HackathonRepository<T extends AbstractHackathon> extends JpaRepository<T, UUID> {
}
