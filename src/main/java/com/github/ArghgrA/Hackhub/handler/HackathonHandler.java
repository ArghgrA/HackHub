package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.AddMentorToHackathonMapper;
import com.github.ArghgrA.Hackhub.dto.mapper.CreateHackathonMapper;
import com.github.ArghgrA.Hackhub.dto.mapper.AddJudgeToHackathonMapper;
import com.github.ArghgrA.Hackhub.dto.request.AddJudgeToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddMentorToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.CreateHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddJudgeToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.response.AddMentorToHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.dto.response.CreateHackathonResponseDTO;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HackathonHandler {
    private final HackathonRepository<DefaultHackathon> hackathonRepository;
    private final UserRepository<Organizer> organizerRepository;
    private final UserRepository<Judge> judgeRepository;
    private final UserRepository<Mentor> mentorRepository;
    private final CreateHackathonMapper createHackathonMapper;
    private final AddJudgeToHackathonMapper judgeToHackathonMapper;
    private final AddMentorToHackathonMapper mentorToHackathonMapper;

    public CreateHackathonResponseDTO createHackathon(CreateHackathonRequestDTO dto) {
        Organizer organizer = organizerRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new RuntimeException("No Organizer with that id"));

        // check if organizer is already registered in one Hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(organizer.getId());
        if(alreadyExistingHackathon.isPresent()) throw new RuntimeException("Organizer is already subscribed to an hackathon");

        // create an Interval. throw Execption if not respect Constraints
        Interval hackathonInterval = new Interval(
                dto.registrationStart(),
                dto.registrationEnd(),
                dto.competitionStart(),
                dto.competitionEnd()
        );

        // build new hackathon with Builder
        DefaultHackathonBuilder builder = new DefaultHackathonBuilder();
        DefaultHackathon hackathon = builder
                .setName(dto.name())
                .setRule(dto.rule())
                .setInterval(hackathonInterval)
                .setMaxTeamMembers(dto.maxTeamMembers())
                .setOrganizer(organizer)
                .getResult();

        organizer.setHackathon(hackathon);

        hackathonRepository.save(hackathon);

        return createHackathonMapper.toResponse(hackathon);
    }

    public AddJudgeToHackathonResponseDTO addJudge(AddJudgeToHackathonRequestDTO dto) {
        // check if Judge exist
        Judge judge = judgeRepository
                .findById(dto.idJudge())
                .orElseThrow(() -> new RuntimeException("no judge with this id"));

        // check if Hackathon exist
        DefaultHackathon hackathon = hackathonRepository
                        .findById(dto.idHackathon())
                        .orElseThrow(() -> new RuntimeException("no hackathon with this id"));

        // check if judge is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(judge.getId());
        if(alreadyExistingHackathon.isPresent()) throw new IllegalStateException("Judge is already subscribed to an hackathon");

        // add Judge to Hackathon
        hackathon.addStaff(judge);
        hackathonRepository.save(hackathon);

        return judgeToHackathonMapper.toResponse(judge);
    }

    public AddMentorToHackathonResponseDTO addMentor(AddMentorToHackathonRequestDTO dto) {
        // check if Mentor exist
        Mentor mentor = mentorRepository
                .findById(dto.idMentor())
                .orElseThrow(() -> new RuntimeException("no mentor with this id"));

        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.idHackathon())
                .orElseThrow(() -> new RuntimeException("no hackathon with this id"));

        // check if judge is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(mentor.getId());
        if(alreadyExistingHackathon.isPresent()) throw new IllegalStateException("Mentor is already subscribed to an hackathon");

        // add Mentor to Hackathon
        hackathon.addStaff(mentor);
        hackathonRepository.save(hackathon);

        return mentorToHackathonMapper.toResponse(mentor);
    }
}
