package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.other.Intervallo;
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
    String name;
    String rule;
    Intervallo intervallo;
    Integer maxTeamMembers;

    @OneToOne @JoinColumn(name = "judge_id")
    Judge judge;

    @OneToMany(mappedBy = "id")
    List<Mentor> mentors;
}
