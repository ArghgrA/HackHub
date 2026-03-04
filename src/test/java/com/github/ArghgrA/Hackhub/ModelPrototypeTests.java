package com.github.ArghgrA.Hackhub;

import com.github.ArghgrA.Hackhub.model.users.DefaultUser;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ModelPrototypeTests {
    @Autowired
    UserRepository<Judge> judgeRepository;

    @Autowired
    UserRepository<Mentor> userRepository;

    @Autowired
    EntityManager entityManager;

    @Test @Commit
    public void basicPrototypeTesting() {
        var user = new Mentor();
        user.setName("nome");
        user.setUsername("nickname");
        user.setEmail("info@mail.com");
        user.setPassword("12345");
        userRepository.save(user);

        /*
        var judge = user.prototype(Judge.class);

        judgeRepository.save(judge);

        assertEquals(user.getName(),judge.getName());
        assertEquals(user.getUsername(),judge.getUsername());
        assertEquals(user.getEmail(),judge.getEmail());
        assertEquals(user.getPassword(),judge.getPassword());

        System.out.println(user);
        System.out.println(judge);
         */
    }
}
