package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HackathonRepository extends JpaRepository<Hackathon,UUID> {
}
