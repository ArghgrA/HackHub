package com.github.ArghgrA.Hackhub;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.other.invites.DefaultInvite;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.users.DefaultUser;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.repository.InviteRepository;
import com.github.ArghgrA.Hackhub.repository.StaffRepository;
import com.github.ArghgrA.Hackhub.repository.TeamRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HackhubApplicationTests {
	@Autowired
	InviteRepository<DefaultInvite> inviteRepository;

	@Autowired
	UserRepository<DefaultUser> userRepository;

	@Autowired
	TeamRepository<DefaultTeam> teamRepository;

	@Autowired
	StaffRepository<AbstractHackathon, AbstractStaff<AbstractHackathon>> staffRepository;

	@Test
	void contextLoads() {
	}

}
