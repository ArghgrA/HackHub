package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import lombok.Getter;

@Getter
public class EvaluationState implements HackathonState {
    public static final EvaluationState INSTANCE = new EvaluationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateEnum.FINISHED.getInstance());
    }
}
