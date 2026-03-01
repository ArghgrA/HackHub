package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.other.Intervallo;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public abstract class AbstractHackathon implements Hackathon<UUID,Integer> {
    UUID id;
    String name;
    String rule;
    Intervallo intervallo;
    Integer maxTeamMembers;
    Judge judge;
    List<Mentor> mentors;
}
