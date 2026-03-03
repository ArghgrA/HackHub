package com.github.ArghgrA.Hackhub;

import com.github.ArghgrA.Hackhub.model.other.invites.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.repository.InviteRepository;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class HackhubApplicationTests {
	@Autowired
	InviteRepository<DefaultInvite> inviteRepository;

	@Autowired
	TeamRepository<DefaultTeam> teamRepository;

	@Autowired
	UserRepository<Judge> staffRepository;

	@Test @Transactional
	void contextLoads() {
		var judge = new Judge();
		judge.setName("Michele Loreti");
		judge.setUsername("ML");
		judge.setEmail("michele@loreti.ml");
		judge.setPassword("sostituitodallAI21");

		staffRepository.save(judge);

		var judge2 = staffRepository.findById(judge.getId()).orElse(null);

		assertNotEquals(null, judge2);
		assertEquals(judge.getName(),judge2.getName());
		assertEquals(judge.getUsername(),judge2.getUsername());
		assertEquals(judge.getEmail(),judge2.getEmail());
		assertEquals(judge.getPassword(),judge2.getPassword());
	}

}
