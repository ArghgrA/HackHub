package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import lombok.Getter;

@Getter
public class RegistrationState implements HackathonState {
    public static final RegistrationState INSTANCE = new RegistrationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateEnum.COMPETITION.getInstance());
    }
}
