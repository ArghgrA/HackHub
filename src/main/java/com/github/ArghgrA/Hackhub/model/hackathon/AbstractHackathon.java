package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.UnactiveState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractHackathon implements Hackathon<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //@OneToOne @JoinColumn(name = "interval_id")
    @Embedded
    private Interval interval;

    @OneToOne @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @OneToOne @JoinColumn(name = "judge_id")
    private Judge judge;

    @OneToMany(mappedBy = "hackathon")
    private List<Mentor> mentors;

    private String name;

    private String rule;

    private Integer maxTeamMembers;

    @Embedded
    private HackathonState state;

    public void addStaff(AbstractStaff s) {
        if(s == null) return;
        s.setHackathon(this);

        switch (s) {
            case Organizer o -> this.organizer = o;
            case Judge j -> this.judge = j;
            case Mentor m -> {
                if (this.mentors == null) this.mentors = new LinkedList<>();
                if (!this.mentors.contains(m)) this.mentors.add(m);
            }
            default -> throw new IllegalArgumentException("");
        }
    }
}
