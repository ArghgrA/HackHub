package com.github.ArghgrA.Hackhub.model.hackathon.builder;

import com.github.ArghgrA.Hackhub.model.abstraction.Team;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;

import java.math.BigDecimal;
import java.util.List;

public interface HackathonBuilder<T extends HackathonBuilder<T>> {
    T setName(String name);
    T setRule(String rule);
    T setInterval(Interval interval);
    T setMaxTeamMembers(Integer maxTeamMembers);
    T setOrganizer(Organizer organizer);
    T setJudge(Judge judge);
    T setMentors(List<Mentor> mentors);
    T setTeams(List<AbstractTeam> teams);
    T setState(HackathonState state);
    T setPrice(BigDecimal price);
    T setPosition(String position);
    T setTeamWinner(AbstractTeam teamWinner);
}
