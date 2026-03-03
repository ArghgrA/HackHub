package com.github.ArghgrA.Hackhub;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class HackathonTest {

    @Autowired
    HackathonRepository<DefaultHackathon> hackathonRepository;

    @Autowired
    UserRepository<Judge> judgeRepository;

    @Autowired
    UserRepository<Mentor> mentorRepository;

    @Test
    @Transactional
    public void createHackathon() {
        var judge = new Judge();
        judge.setName("Mario Rossi");
        judge.setUsername("MR");
        judge.setEmail("mario@rossi.mr");
        judge.setPassword("12345");

        judgeRepository.save(judge);

        var mentor = new Mentor();
        mentor.setName("Ciccio C");
        mentor.setUsername("CC");
        mentor.setEmail("ciccio@c.cc");
        mentor.setPassword("12345");

        mentorRepository.save(mentor);


        var hackathon = new DefaultHackathon();
        hackathon.setName("Hackathon");
        hackathon.setRule("usa l'ai");
        hackathon.setMaxTeamMembers(10);
        hackathon.addJudge(judge);
        hackathon.addMentor(mentor);
        hackathon.setIntervallo(null);

        hackathonRepository.save(hackathon);

        var hackathon2 = hackathonRepository.findById(hackathon.getId()).orElse(null);


        assertNotEquals(null,hackathon2);
        assertEquals(hackathon.getId(), hackathon2.getId());
        assertEquals(hackathon.getName(), hackathon2.getName());
        assertEquals(hackathon.getRule(), hackathon2.getRule());
        assertEquals(hackathon.getMaxTeamMembers(), hackathon2.getMaxTeamMembers());
        assertEquals(judge, hackathon2.getJudge());
        assertEquals(mentor, hackathon2.getMentors().getFirst());
        assertEquals(hackathon.getIntervallo(), hackathon2.getIntervallo());
        assertEquals(hackathon.getState(),hackathon2.getState());

    }
}
