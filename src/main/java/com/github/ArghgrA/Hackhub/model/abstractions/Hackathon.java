package com.github.ArghgrA.Hackhub.model.abstractions;

import com.github.ArghgrA.Hackhub.model.other.Intervallo;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import jakarta.persistence.Entity;

import java.util.List;

public interface Hackathon<I extends Comparable<I>> {
    I getId();
    String getName();
    String getRule();
    Intervallo getIntervallo();
    Integer getMaxTeamMembers();
    Judge getJudge();
    List<Mentor> getMentors();
}
