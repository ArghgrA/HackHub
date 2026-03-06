package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;

public interface HackathonState {
    void updateState(AbstractHackathon h);
}
