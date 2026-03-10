package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
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

    ;
}
