package com.github.ArghgrA.Hackhub.model.other.message;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DefaultReport extends AbstractMessage<String,Mentor,AbstractHackathon> {
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor sender;

    @ManyToOne @JoinColumn(name = "hackathon_id")
    private AbstractHackathon receiver;

    @OneToOne @JoinColumn(name = "team_id")
    private AbstractTeam team;

    private String message;
}
