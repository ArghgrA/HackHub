package com.github.ArghgrA.Hackhub.model.abstraction;

import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;

import java.math.BigDecimal;
import java.util.List;

public interface Hackathon<I extends Comparable<I>> {
    I getId();
    String getName();
    String getRule();
    Interval getInterval();
    Integer getMaxTeamMembers();
    Organizer getOrganizer();
    Judge getJudge();
    List<Mentor> getMentors();
    BigDecimal getPrice();
    String getPosition();
}
