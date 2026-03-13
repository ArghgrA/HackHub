package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstraction.Hackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateConverter;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractHackathon implements Hackathon<UUID>/*, HackathonState */ {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Embedded
    private Interval interval;

    @Setter(AccessLevel.NONE)
    @OneToOne @JoinColumn(name = "organizer_id")
    private Organizer organizer;

    @Setter(AccessLevel.NONE)
    @OneToOne @JoinColumn(name = "judge_id")
    private Judge judge;

    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mentor> mentors = new LinkedList<>();

    private String name;

    private String rule;

    private Integer maxTeamMembers;

    private BigDecimal price;

    private String position;

    @ManyToMany
    @JoinTable(name = "team_hackathon",
            joinColumns = @JoinColumn(name = "hackathon_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<AbstractTeam> teams = new LinkedList<>();

    @Convert(converter = HackathonStateConverter.class)
    private HackathonState state;

    public void addStaff(AbstractStaff s) {
        if(s == null) return;
        s.setHackathon(this);

        switch (s) {
            case Organizer o -> this.organizer = o;
            case Judge j -> this.judge = j;
            case Mentor m -> {
                if (!this.mentors.contains(m)) this.mentors.add(m);
            }
            default -> throw new IllegalArgumentException("Unknown Staff");
        }
    }

    public void addTeam(AbstractTeam team){
        if(team == null) return;
        if (!teams.contains(team)) {
            teams.add(team);
            team.addHackathon(this);
        }
    }

    public void updateState() {
        state.updateState(this);
    };
}
