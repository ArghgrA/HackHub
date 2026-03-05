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
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.hackathon.state.UnactiveState;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
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
                .orElseThrow(() -> new EntityNotFoundException("No Organizer with that id"));

        // check if organizer is already registered in one Hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(organizer.getId());
        if(alreadyExistingHackathon.isPresent()) throw new AlreadyExistingException("Organizer is already subscribed to an hackathon");

        // create an Interval. throw IllegalDateException if not respect Constraints
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
                .setState(new UnactiveState())
                .getResult();

        organizer.setHackathon(hackathon);

        hackathonRepository.save(hackathon);

        return createHackathonMapper.toResponse(hackathon);
    }

    public AddJudgeToHackathonResponseDTO addJudge(AddJudgeToHackathonRequestDTO dto) {
        Organizer organizer = organizerRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("No Organizer with that id"));

        // check if Hackathon exist
        DefaultHackathon hackathon = hackathonRepository
                .findHackathonByStaff(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("Hackathon not exist"));

        // check if Hackathon already has a Judge
        if (hackathon.getJudge() != null) throw new AlreadyExistingException("Hakathon already has a Judge");

        // check if Judge exist
        Judge judge = judgeRepository
                .findById(dto.judgeId())
                .orElseThrow(() -> new EntityNotFoundException("Judge not exist"));

        // check if passed Judge is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(judge.getId());
        if(alreadyExistingHackathon.isPresent()) throw new AlreadyExistingException("Judge already subscribed to one Hackathon");

        // add Judge to Hackathon and persist in db
        hackathon.addStaff(judge);
        hackathonRepository.save(hackathon);

        return judgeToHackathonMapper.toResponse(judge);
    }

    public AddMentorToHackathonResponseDTO addMentor(AddMentorToHackathonRequestDTO dto) {
        Organizer organizer = organizerRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("No Organizer with that id"));

        // check if Hackathon exist
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("HackathonNotExist"));

        // check if Mentor exist
        Mentor mentor = mentorRepository
                .findById(dto.mentorId())
                .orElseThrow(() -> new EntityNotFoundException("MentorNotExist"));


        // check if mentor is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(mentor.getId());
        if(alreadyExistingHackathon.isPresent()) throw new AlreadyExistingException("Mentor already subscribed to one Hackathon");

        // add Mentor to Hackathon and persist in db
        hackathon.addStaff(mentor);
        hackathonRepository.save(hackathon);

        return mentorToHackathonMapper.toResponse(mentor);
    }
}
