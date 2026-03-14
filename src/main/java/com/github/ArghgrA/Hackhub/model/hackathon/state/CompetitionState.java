package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
public class CompetitionState implements HackathonState {
    public static final CompetitionState INSTANCE = new CompetitionState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.EVALUATION.getInstance());
    }

    @Override
    public String getName() {
        return "COMPETITION";
    }

    @Override
    public void setTeamWinner(AbstractHackathon h,AbstractTeam t) {
        throw new IllegalStateException(String.format("Cannot Set team winner in state %s",this.getName()));
    }
}
