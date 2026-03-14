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

    public void setTeamWinner(AbstractHackathon h,AbstractTeam w) {
        h.teamWinner = w;
        //Remove Organizer
        h.getOrganizer().setHackathon(null);
        h.setOrganizer(null);
        //Remove Judge
        h.getJudge().setHackathon(null);
        h.setJudge(null);
        //Remove Mentor
        for(Mentor m: h.getMentors()){
            m.setHackathon(null);
        }
        h.setMentors(null);
        //Remove Team
        h.getTeams().forEach(t -> t.getHackathons().remove(h));
        h.setTeams(null);
    }
}
