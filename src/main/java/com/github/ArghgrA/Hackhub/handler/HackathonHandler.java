package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addHackathonDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addJudgeDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addMentorDTO;
import com.github.ArghgrA.Hackhub.dto.DTOCreazione.addTeamDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.HackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.DTOResponse.TeamResponseDTO;
import com.github.ArghgrA.Hackhub.dto.Mapper.HackathonMapper;
import com.github.ArghgrA.Hackhub.dto.Mapper.JudgeMapper;
import com.github.ArghgrA.Hackhub.dto.Mapper.MentorMapper;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.users.AbstractUser;
import com.github.ArghgrA.Hackhub.model.users.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class HackathonHandler {
    private HackathonRepository<AbstractHackathon> hackathonRepository;

    private HackathonMapper hackathonMapper;

    private UserRepository<AbstractUser> userRepository;

    private JudgeMapper judgeMapper;

    private MentorMapper mentorMapper;
    public HackathonHandler(
            HackathonRepository<AbstractHackathon> hackathonRepository,
            HackathonMapper hackathonMapper,
            UserRepository<AbstractUser> userRepository,
            JudgeMapper judgeMapper,
            MentorMapper mentorMapper
    ) {
        this.hackathonRepository = hackathonRepository;
        this.hackathonMapper = hackathonMapper;
        this.userRepository = userRepository;
        this.judgeMapper = judgeMapper;
        this.mentorMapper = mentorMapper;
    }

    public HackathonResponseDTO creaHackathon(addHackathonDTO dto){
        AbstractHackathon hackathon = hackathonMapper.toEntiy(dto);
        AbstractHackathon saved = hackathonRepository.save(hackathon);
        return hackathonMapper.toDTO(saved);
    }

    public void addJudgeToHackathon(addJudgeDTO dto) {
        Judge judge = (Judge) userRepository.findById(dto.judgeId())
                .orElseThrow(() -> new RuntimeException("Judge not found"));

        AbstractHackathon hackathon = hackathonRepository.findById(dto.hackathonId())
                .orElseThrow(() -> new RuntimeException("Hackathon not found"));

        if (judge.getHackathon() != null) {
            throw new IllegalStateException("Judge is already assigned to a hackathon");
        }

        judgeMapper.fromDTO(dto, judge, hackathon);

        userRepository.save(judge);
    }

    public void addMentorToHackathon(addMentorDTO dto) {

        Mentor mentor = (Mentor) userRepository.findById(dto.mentorId())
                .orElseThrow(() -> new RuntimeException("Mentor not found"));

        AbstractHackathon hackathon = hackathonRepository.findById(dto.hackathonId())
                .orElseThrow(() -> new RuntimeException("Hackathon not found"));

        if (mentor.getHackathon() != null) {
            throw new IllegalStateException("Mentor is already assigned to a hackathon");
        }

        mentorMapper.fromDTO(dto, mentor, hackathon);

        userRepository.save(mentor);
    }
}
