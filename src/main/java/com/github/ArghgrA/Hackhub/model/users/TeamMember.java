package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TeamMember extends AbstractUser {
    @ManyToOne @JoinColumn(name = "team_id")
    DefaultTeam team;
}
