package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import lombok.Getter;

@Getter
public class EvaluationState implements HackathonState {
    public static final EvaluationState INSTANCE = new EvaluationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.PROCLAMATION.getInstance());
    }

    @Override
    public String getName() {
        return "EVALUATION";
    }
}
