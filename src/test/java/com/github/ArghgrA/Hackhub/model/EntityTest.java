package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class EntityTest {
    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Autowired
    HackathonRepository<AbstractHackathon> hRepository;

    @Test @Transactional
    public void testPersistence() {
        // create User
        var usr = new DefaultUser();
        usr.setName("foo");
        usr.setUsername("foobar");
        usr.setEmail("foo@bar");
        usr.setPassword("pwd");

        // persist in db
        usrRepository.save(usr);

        // check if persistence work
        var usr_copy = usrRepository.findById(usr.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        assertNotEquals(null,usr_copy);
        assertEquals(usr,usr_copy);

        // create some Staff
        var o = new Organizer();
        o.setName("foo");
        o.setUsername("foobar");
        o.setEmail("foo@bar");
        o.setPassword("pwd");

        var j = new Judge();
        j.setUsername("foobar");
        j.setEmail("foo@bar");
        j.setPassword("pwd");

        var m = new Mentor();
        m.setUsername("foobar");
        m.setEmail("foo@bar");
        m.setPassword("pwd");

        var m2 = new Mentor();
        m2.setUsername("foobar");
        m2.setEmail("foo@bar");
        m2.setPassword("pwd");


        // create Hackathon via HackathonBuilder
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
                        )
                )
                .setOrganizer(o)
                .setJudge(j)
                .setMentors(List.of(m,m2))
                .getResult();

        // persist in db
        hRepository.save(h);

        // check if persistence work
        var h_copy = hRepository.findById(h.getId()).orElseThrow(() -> new RuntimeException("Hackathon not found"));
        assertNotEquals(null,h_copy);
        assertEquals(h,h_copy);
    }
}
