package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractHackathon implements Hackathon<UUID> {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToOne @JoinColumn(name = "interval_id")
    Interval intervallo;

    @OneToOne @JoinColumn(name = "judge_id")
    Judge judge;

    @OneToMany(mappedBy = "id")
    List<Mentor> mentors;

    String name;

    String rule;

    Integer maxTeamMembers;
}
