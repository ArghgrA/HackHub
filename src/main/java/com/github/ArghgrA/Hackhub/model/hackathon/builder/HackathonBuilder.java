package com.github.ArghgrA.Hackhub.model.hackathon.builder;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

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
