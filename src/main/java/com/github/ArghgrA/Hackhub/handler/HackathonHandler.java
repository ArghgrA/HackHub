package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.*;
import com.github.ArghgrA.Hackhub.dto.model.HackathonDTO;
import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddJudgeToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.AddMentorToHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.dto.request.CreateHackathonRequestDTO;
import com.github.ArghgrA.Hackhub.exception.AlreadyExistingException;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.builder.DefaultHackathonBuilder;
import com.github.ArghgrA.Hackhub.model.hackathon.state.InactiveState;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateEnum;
import com.github.ArghgrA.Hackhub.model.other.Interval;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;
import com.github.ArghgrA.Hackhub.repository.HackathonRepository;
import com.github.ArghgrA.Hackhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HackathonHandler {
    private final HackathonRepository<DefaultHackathon> hackathonRepository;
    private final UserRepository<AbstractStaff> staffRepository;
    private final StaffMapper staffMapper;
    private final HackathonMapper hackathonMapper;

    public HackathonDTO createHackathon(CreateHackathonRequestDTO dto) {
        Organizer organizer = (Organizer) staffRepository
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
                .setState(new InactiveState())
                .getResult();

        organizer.setHackathon(hackathon);

        hackathonRepository.save(hackathon);

        return hackathonMapper.toDTO(hackathon);
    }

    public StaffDTO addJudge(AddJudgeToHackathonRequestDTO dto) {
        Organizer organizer = (Organizer) staffRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("No Organizer with that id"));

        // check if Hackathon exist
        DefaultHackathon hackathon = hackathonRepository
                .findHackathonByStaff(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("Hackathon not exist"));

        // check if Hackathon already has a Judge
        if (hackathon.getJudge() != null) throw new AlreadyExistingException("Hakathon already has a Judge");

        // check if Judge exist
        Judge judge = (Judge) staffRepository
                .findById(dto.judgeId())
                .orElseThrow(() -> new EntityNotFoundException("Judge not exist"));

        // check if passed Judge is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(judge.getId());
        if(alreadyExistingHackathon.isPresent()) throw new AlreadyExistingException("Judge already subscribed to one Hackathon");

        // add Judge to Hackathon and persist in db
        hackathon.addStaff(judge);
        hackathonRepository.save(hackathon);

        return staffMapper.toDTO(judge);
    }

    public StaffDTO addMentor(AddMentorToHackathonRequestDTO dto) {
        Organizer organizer = (Organizer) staffRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("No Organizer with that id"));

        // check if Hackathon exist
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.organizerId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // check if Mentor exist
        Mentor mentor = (Mentor) staffRepository
                .findById(dto.mentorId())
                .orElseThrow(() -> new EntityNotFoundException("No Mentor with that id"));


        // check if mentor is already in one hackathon
        Optional<DefaultHackathon> alreadyExistingHackathon = hackathonRepository.findHackathonByStaff(mentor.getId());
        if(alreadyExistingHackathon.isPresent()) throw new AlreadyExistingException("Mentor already subscribed to one Hackathon");

        // add Mentor to Hackathon and persist in db
        hackathon.addStaff(mentor);
        hackathonRepository.save(hackathon);

        return staffMapper.toDTO(mentor);
    }

    @Transactional
    @Scheduled(cron = "0 */30 * * * *")
    public void updateHackathonState() {
        LocalDateTime now = LocalDateTime.now();

        // retrieve hackathons whose temporal windows (registration or competition) include the current time.
        hackathonRepository.findHackathonByInstant(now)
                .stream()
                // make sure that only hackathon that are in REGISTRATION or COMPETITION state can be updated
                .filter(h -> h.getState() == HackathonStateEnum.REGISTRATION.getInstance() ||
                        h.getState() == HackathonStateEnum.COMPETITION.getInstance())
                .forEach(DefaultHackathon::updateState);
    }
}
