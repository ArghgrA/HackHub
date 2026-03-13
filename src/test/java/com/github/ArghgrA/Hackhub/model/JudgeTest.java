package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class JudgeTest {

    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Test
    @Commit
    public void Test(){
        var j = new Judge();
        j.setUsername("foobar");
        j.setEmail("foo@bar");
        j.setPassword("pwd");

        // persist in db
        usrRepository.save(j);
    }
}
