package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.abstractions.Team;
import com.github.ArghgrA.Hackhub.model.users.TeamMember;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public abstract class AbstractTeam implements Team<UUID> {
    UUID id;
    String name;
    List<TeamMember> members;
}
