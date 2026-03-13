package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class UserTest {
    @Autowired
    UserRepository<AbstractUser> usrRepository;

    @Test
    @Commit
    public void Test(){
        // create User
        var usr = new DefaultUser();
        usr.setName("foo1");
        usr.setUsername("foobar1");
        usr.setEmail("foo1@bar");
        usr.setPassword("pwd");

        // persist in db
        usrRepository.save(usr);
    }
}
