package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import lombok.Getter;

@Getter
public class InactiveState implements HackathonState{
    public static final InactiveState INSTANCE = new InactiveState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.REGISTRATION.getInstance());
    }

    @Override
    public String getName() {
        return "INACTIVE";
    }

    public void setTeamWinner(AbstractHackathon h,AbstractTeam t) {
        throw new IllegalStateException(String.format("Cannot Set team winner in state %s",this.getName()));
    }
}
