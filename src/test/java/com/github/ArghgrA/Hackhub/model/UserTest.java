package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Autowired
    TeamRepository<AbstractTeam> teamRepository;

    @Test
    @Commit
    public void Test(){
        // create User
        /*var usr = new DefaultUser();
        usr.setName("foo1");
        usr.setUsername("foobar1");
        usr.setEmail("foo1@bar");
        usr.setPassword("pwd");
         */

        // persist in db
        //usrRepository.save(usr);

        var member = new TeamMember();
        member.setTeam(teamRepository.findById(UUID.fromString("033e9dd5-b4c6-44bf-a33b-6931f83cf786")).get());
        member.setName("mario");
        usrRepository.save(member);
    }
}
