package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import lombok.Getter;

@Getter
public class EvaluationState implements HackathonState {
    public static final EvaluationState INSTANCE = new EvaluationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.FINISHED.getInstance());
    }

    @Override
    public String getName() {
        return "EVALUATION";
    }
}
