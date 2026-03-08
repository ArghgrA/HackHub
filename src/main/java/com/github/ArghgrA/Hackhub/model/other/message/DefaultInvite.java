package com.github.ArghgrA.Hackhub.model.other.message;

import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
@Entity
public class DefaultInvite extends AbstractMessage<String, AbstractTeam, AbstractUser> {
    @ManyToOne
    @JoinColumn(name = "team_id")
    private AbstractTeam sender;

    @ManyToOne @JoinColumn(name = "user_id")
    private AbstractUser receiver;

    private String message;
}
