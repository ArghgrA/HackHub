package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.abstraction.Team;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.Score;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
public class CompleteTest {

    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Autowired
    HackathonRepository<AbstractHackathon> hRepository;

    @Autowired
    TeamRepository<AbstractTeam> teamRepository;

    @Autowired
    SubmissionRepository<DefaultSubmission> submissionRepository;

    @Autowired
    EvaluationRepository<DefaultEvaluation> evaluationRepository;

    @Test
    @Commit
    public void testPersistence() {
        // DefaultUser
        var usr = new DefaultUser();
        usr.setName("foo1");
        usr.setUsername("foobar1");
        usr.setEmail("foo1@bar");
        usr.setPassword("pwd");
        usrRepository.save(usr);

        // DefaultUser2
        var usr2 = new DefaultUser();
        usr2.setName("foo2");
        usr2.setUsername("foobar2");
        usr2.setEmail("foo2@bar");
        usr2.setPassword("pwd");
        usrRepository.save(usr2);

        // Organizzatore
        var o = new Organizer();
        o.setName("Organizzatore");
        o.setUsername("organizer");
        o.setEmail("organizer@bar");
        o.setPassword("pwd");
        usrRepository.save(o);

        // Mentore
        var m = new Mentor();
        m.setName("Mentore");
        m.setUsername("mentor");
        m.setEmail("mentor@bar");
        m.setPassword("pwd");
        usrRepository.save(m);

        // Giudice
        var j = new Judge();
        j.setName("Giudice");
        j.setUsername("judge");
        j.setEmail("judge@bar");
        j.setPassword("pwd");
        usrRepository.save(j);

        // Hackathon
        var h_build = new DefaultHackathonBuilder();
        var h = h_build
                .setName("hackathon")
                .setRule("rules")
                .setMaxTeamMembers(10)
                .setInterval(new Interval(
                        LocalDateTime.parse("2027-06-01T10:00:00"),
                        LocalDateTime.parse("2027-06-01T12:00:00"),
                        LocalDateTime.parse("2027-06-01T13:00:00"),
                        LocalDateTime.parse("2027-06-01T15:00:00")
                ))
                .getResult();

        // Aggiungi lo staff all'hackathon (ORA sono entità persistenti)
        h.addStaff(o);
        h.addStaff(j);
        h.addStaff(m);

        // Salva l'hackathon
        hRepository.save(h);

        // Team
        var team = new DefaultTeam();
        team.setName("pwnc4m");

        // Trasforma l'utente in TeamMember e aggiungilo al team
        var teamMember = usr.transform(TeamMember.class);
        team.addMember(teamMember);

        // Salva il team
        teamRepository.save(team);

        // Aggiungi il team all'hackathon
        h.addTeam(team);
        hRepository.save(h); // Aggiorna l'hackathon con il team

        // Submission
        var submission = new DefaultSubmission();
        submission.setSender(team);
        submission.setReceiver(h);
        submission.setMessage(null);
        submissionRepository.save(submission);

        // Evaluation
        var evaluation = new DefaultEvaluation();
        evaluation.setSender(j);
        evaluation.setReceiver(h);
        evaluation.setSubmission(submission);
        evaluation.setMessage(new Score(10, "Ottimo Lavoro"));
        evaluationRepository.save(evaluation);
    }
}