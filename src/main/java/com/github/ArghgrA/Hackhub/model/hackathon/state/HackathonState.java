package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;

public interface HackathonState {
    void updateState(AbstractHackathon h);
    String getName();
}
