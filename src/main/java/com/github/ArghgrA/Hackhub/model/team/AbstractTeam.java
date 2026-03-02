package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.abstractions.Team;
import com.github.ArghgrA.Hackhub.model.abstractions.User;
import com.github.ArghgrA.Hackhub.model.users.TeamMember;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractTeam implements Team<UUID> {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToMany(mappedBy = "id")
    List<TeamMember> members;

    String name;
}
