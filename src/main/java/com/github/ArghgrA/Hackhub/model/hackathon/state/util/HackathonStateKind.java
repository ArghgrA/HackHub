package com.github.ArghgrA.Hackhub.model.hackathon.state.util;

import com.github.ArghgrA.Hackhub.model.hackathon.state.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum HackathonStateKind {
    INACTIVE(InactiveState.INSTANCE),
    REGISTRATION(RegistrationState.INSTANCE),
    COMPETITION(CompetitionState.INSTANCE),
    EVALUATION(EvaluationState.INSTANCE),
    FINISHED(FinishedState.INSTANCE);

    private final HackathonState instance;

    public static HackathonStateKind fromState(HackathonState state) {
        for (HackathonStateKind e : values()) {
            //if (e.instance == state) return e;
            //if(e.instance.equals(state)) return e;
            if (e.instance.getClass().equals(state.getClass())) return e;
        }

        throw new IllegalArgumentException("Unknown HackathonState" + state.getClass().getName());
    }
}
