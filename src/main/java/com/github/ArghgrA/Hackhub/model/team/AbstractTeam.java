package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.abstraction.Team;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTeam implements Team<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id")
    private List<TeamMember> members;

    private String name;

    public void addMember(TeamMember member) {
        if (member == null) return;
        if (members == null) members = new LinkedList<>();
        if (!members.contains(member)) members.add(member);
    }
}
