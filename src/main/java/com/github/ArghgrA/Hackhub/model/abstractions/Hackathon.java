package com.github.ArghgrA.Hackhub.model.abstractions;

import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;

import java.util.List;

public interface Hackathon<I extends Comparable<I>> {
    I getId();
    String getName();
    String getRule();
    Interval getIntervallo();
    Integer getMaxTeamMembers();
    Judge getJudge();
    List<Mentor> getMentors();
}
