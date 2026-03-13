package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class OrganizerTest {
    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Test
    @Commit
    public void Test(){
        // create User
        var o = new Organizer();
        o.setName("foo");
        o.setUsername("foobar");
        o.setEmail("foo@bar");
        o.setPassword("pwd");

        // persist in db
        usrRepository.save(o);
    }
}
