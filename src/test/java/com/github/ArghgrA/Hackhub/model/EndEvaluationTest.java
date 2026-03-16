package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
public class EndEvaluationTest {
    final Interval VALID_INTERVAL =
            new Interval(LocalDateTime.now().plusDays(31).truncatedTo(ChronoUnit.HOURS), LocalDateTime.now().plusDays(31).plusHours(1).truncatedTo(ChronoUnit.HOURS), LocalDateTime.now().plusDays(31).plusHours(2).truncatedTo(ChronoUnit.HOURS), LocalDateTime.now().plusDays(31).plusHours(3).truncatedTo(ChronoUnit.HOURS));

    @Autowired
    HackathonRepository<DefaultHackathon> h_rep;

    @Autowired
    SubmissionRepository<DefaultSubmission> s_rep;

    @Autowired
    EvaluationRepository<DefaultEvaluation> e_rep;

    @Autowired
    TeamRepository<DefaultTeam> t_rep;

    @Autowired
    UserRepository<Judge> j_rep;

    @Autowired
    UserRepository<TeamMember> tm_rep;

    @Test @Commit
    public void endEvaluation(){
        // create hackathon and save in db
        var h = new DefaultHackathonBuilder()
                .setName("hackathon2026")
                .setRule("foobar")
                .setInterval(VALID_INTERVAL)
                .setState(HackathonStateKind.EVALUATION.getInstance())
                .getResult();
        h_rep.save(h);

        // create team memebrs and save in db
        var tm1 = new TeamMember();
        tm1.setName("teamItalyMember1");
        var tm2 = new TeamMember();
        tm2.setName("teamItalyMember2");
        tm_rep.saveAll(List.of(tm1,tm2));

        // create team, add team members, save in db
        var t = new DefaultTeam();
        t.setName("teamItaly");
        t.addHackathon(h);
        t_rep.save(t);
        t.addMember(tm1);
        t.addMember(tm2);
        t_rep.save(t);

        // create submissions, link to team and hackathon, save in db
        var s1 = new DefaultSubmission();
        s1.setMessage(null);
        s1.setSender(t);
        s1.setReceiver(h);
        s_rep.save(s1);

        var s2 = new DefaultSubmission();
        s2.setMessage(null);
        s2.setSender(t);
        s2.setReceiver(h);
        s_rep.save(s2);

        // create judge, save in db, add it to hackathon
        var j = new Judge();
        j.setName("ML");
        j.setUsername("ML-BOT");
        j_rep.save(j);

        h.addStaff(j);
        h_rep.save(h);

        // create evaluation, save in db, link them to submissions
        var e1 = new DefaultEvaluation();
        var e2 = new DefaultEvaluation();
        e1.setSender(j);
        e1.setReceiver(h);
        e2.setSender(j);
        e2.setReceiver(h);
        e1.setSubmission(s1);
        e2.setSubmission(s2);
        e_rep.save(e1);
        e_rep.save(e2);
    }
}
