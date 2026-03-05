package com.github.ArghgrA.Hackhub.model.other.invites;

import com.github.ArghgrA.Hackhub.model.abstraction.Invite;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractInvite implements Invite<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne @JoinColumn(name = "team_id")
    private AbstractTeam team;

    @ManyToOne @JoinColumn(name = "user_id")
    private DefaultUser user;

    private String message;
}
