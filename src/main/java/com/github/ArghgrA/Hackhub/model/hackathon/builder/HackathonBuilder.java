package com.github.ArghgrA.Hackhub.model.hackathon.builder;

import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;

import java.util.List;

public interface HackathonBuilder<T extends HackathonBuilder<T>> {
    T setName(String name);
    T setRule(String rule);
    T setInterval(Interval interval);
    T setMaxTeamMembers(Integer maxTeamMembers);
    T setOrganizer(Organizer organizer);
    T setJudge(Judge judge);
    T setMentors(List<Mentor> mentors);
    T setState(HackathonState state);
}
