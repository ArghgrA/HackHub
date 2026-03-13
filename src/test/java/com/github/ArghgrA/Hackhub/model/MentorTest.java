package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class MentorTest {

    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Test
    @Commit
    public void Test(){
        var m = new Mentor();
        m.setUsername("foobar");
        m.setEmail("foo@bar");
        m.setPassword("pwd");

        // persist in db
        usrRepository.save(m);
    }

}
