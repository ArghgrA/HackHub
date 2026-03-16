package com.github.ArghgrA.Hackhub.model.hackathon.state;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import lombok.Getter;

@Getter
public class ProclamationState implements HackathonState{
    public static final ProclamationState INSTANCE = new ProclamationState();

    @Override
    public void updateState(AbstractHackathon h) {
        h.setState(HackathonStateKind.FINISHED.getInstance());
    }

    @Override
    public String getName() {
        return "PROCLAMATION";
    }
}
