package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
@Entity
public class TeamMember extends AbstractUser {
    @ManyToOne @JoinColumn(name = "team_id")
    private DefaultTeam team;
}
