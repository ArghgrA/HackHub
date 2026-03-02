package com.github.ArghgrA.Hackhub.model.other.invites;

import com.github.ArghgrA.Hackhub.model.abstractions.Invite;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.users.DefaultUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractInvite implements Invite<UUID> {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne @JoinColumn(name = "team_id")
    DefaultTeam team;

    @ManyToOne @JoinColumn(name = "user_id")
    DefaultUser user;

    String text;
}
