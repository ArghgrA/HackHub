package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import lombok.Getter;

@Getter
public class InactiveState implements HackathonState{
    public static final InactiveState INSTANCE = new InactiveState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateEnum.REGISTRATION.getInstance());
    };
}
