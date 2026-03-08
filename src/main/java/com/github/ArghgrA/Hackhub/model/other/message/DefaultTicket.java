package com.github.ArghgrA.Hackhub.model.other.message;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DefaultTicket extends AbstractMessage<String,AbstractTeam,AbstractHackathon> {
    @ManyToOne
    @JoinColumn(name = "team_id")
    private AbstractTeam sender;

    @ManyToOne @JoinColumn(name = "hackathon_id")
    private AbstractHackathon receiver;

    private String message;
}
