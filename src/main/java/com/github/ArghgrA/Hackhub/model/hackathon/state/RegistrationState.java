package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import lombok.Getter;

@Getter
public class RegistrationState implements HackathonState {
    public static final RegistrationState INSTANCE = new RegistrationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.COMPETITION.getInstance());
    }

    @Override
    public String getName() {
        return "REGISTRATION";
    }
}
