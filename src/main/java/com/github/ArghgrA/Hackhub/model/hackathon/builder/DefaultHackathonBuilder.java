package com.github.ArghgrA.Hackhub.model.hackathon.builder;

import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.UnactiveState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;

import java.util.LinkedList;
import java.util.List;

public class DefaultHackathonBuilder implements HackathonBuilder<DefaultHackathonBuilder> {
    private DefaultHackathon hackathon;

    public DefaultHackathonBuilder() {
        hackathon = new DefaultHackathon();
    };

    public DefaultHackathonBuilder setName(String name) {
        hackathon.setName(name);
        return this;
    }

    public DefaultHackathonBuilder setRule(String rule) {
        hackathon.setRule(rule);
        return this;
    }

    public DefaultHackathonBuilder setInterval(Interval interval) {
        hackathon.setInterval(interval);
        return this;
    }

    public DefaultHackathonBuilder setMaxTeamMembers(Integer maxTeamMembers) {
        hackathon.setMaxTeamMembers(maxTeamMembers);
        return this;
    }

    public DefaultHackathonBuilder setOrganizer(Organizer organizer) {
        hackathon.addStaff(organizer);
        return this;
    }

    public DefaultHackathonBuilder setJudge(Judge judge) {
        hackathon.addStaff(judge);
        return this;
    }

    public DefaultHackathonBuilder setMentors(List<Mentor> mentors) {
        hackathon.setMentors(mentors);
        return this;
    }

    public DefaultHackathonBuilder setState(HackathonState state) {
        hackathon.setState(state);
        return this;
    }

    public DefaultHackathon getResult() {
        DefaultHackathon tmp = hackathon;

        // default values for some fields
        if (tmp.getState() == null) tmp.setState(new UnactiveState());
        if (tmp.getMentors() == null) tmp.setMentors(new LinkedList<>());

        hackathon = new DefaultHackathon();
        return tmp;
    }
}
