package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.UnactiveState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
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
    private Interval intervallo;

    @OneToOne @JoinColumn(name = "judge_id")
    private Judge judge;

    @OneToMany(mappedBy = "hackathon")
    @Setter(AccessLevel.NONE)
    private List<Mentor> mentors;

    private String name;

    private String rule;

    private Integer maxTeamMembers;

    @Embedded
    private HackathonState state;


    protected AbstractHackathon(){
        this.state = new UnactiveState();
        this.mentors = new LinkedList<>();
    }

    public void addMentor(Mentor m){
        if(mentors.contains(m)) return;
        this.mentors.add(m);
        m.setHackathon(this);
    }

    public void addJudge(Judge j){
        this.judge = j;
        j.setHackathon(this);
    }


}
