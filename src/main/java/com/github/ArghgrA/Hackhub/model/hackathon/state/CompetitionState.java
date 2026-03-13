package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import lombok.Getter;

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
}
