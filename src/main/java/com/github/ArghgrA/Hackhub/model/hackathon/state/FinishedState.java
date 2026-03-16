package com.github.ArghgrA.Hackhub.model.hackathon.state;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import lombok.Getter;

@Getter
public class FinishedState implements HackathonState {
    public static final FinishedState INSTANCE = new FinishedState();

    @Override
    public void updateState(AbstractHackathon h) {
        throw new RuntimeException("Cannot update Hackathon in Finished state");
    }

    @Override
    public String getName() {
        return "FINISHED";
    }
}
