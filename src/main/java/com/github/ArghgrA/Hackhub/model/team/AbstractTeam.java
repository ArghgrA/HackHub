package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.abstractions.Team;
import com.github.ArghgrA.Hackhub.model.abstractions.User;
import com.github.ArghgrA.Hackhub.model.users.TeamMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTeam implements Team<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "id")
    private List<TeamMember> members;

    private String name;
}
