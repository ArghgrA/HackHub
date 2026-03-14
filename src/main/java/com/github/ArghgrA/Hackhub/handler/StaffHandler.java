package com.github.ArghgrA.Hackhub.handler;

import com.github.ArghgrA.Hackhub.dto.mapper.*;
import com.github.ArghgrA.Hackhub.dto.model.EvaluationDTO;
import com.github.ArghgrA.Hackhub.dto.model.ReportDTO;
import com.github.ArghgrA.Hackhub.dto.model.StaffDTO;
import com.github.ArghgrA.Hackhub.dto.model.TicketDTO;
import com.github.ArghgrA.Hackhub.dto.request.*;
import com.github.ArghgrA.Hackhub.exception.EntityNotFoundException;
import com.github.ArghgrA.Hackhub.exception.PaymentException;
import com.github.ArghgrA.Hackhub.model.hackathon.DefaultHackathon;
import com.github.ArghgrA.Hackhub.model.hackathon.state.util.HackathonStateKind;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultReport;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.other.message.ticket.DefaultTicket;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.DefaultEvaluation;
import com.github.ArghgrA.Hackhub.model.other.message.evaluation.Score;
import com.github.ArghgrA.Hackhub.model.other.message.ticket.TicketStateKind;
import com.github.ArghgrA.Hackhub.model.other.payment.address.AbstractPaymentAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.address.PaymentAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.strategy.PaymentStrategy;
import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffHandler {
    private final HackathonRepository<DefaultHackathon> hackathonRepository;
    private final UserRepository<DefaultUser> userRepository;
    private final UserRepository<AbstractStaff> staffRepository;
    private final TeamRepository<DefaultTeam> teamRepository;
    private final ReportRepository<DefaultReport> reportRepository;
    private final EvaluationRepository<DefaultEvaluation> evaluationRepository;
    private final SubmissionRepository<DefaultSubmission> submissionRepository;
    private final TicketRepository<DefaultTicket> ticketRepository;
    private PaymentStrategy paymentStrategy;

    private final StaffMapper staffMapper;
    private final ReportMapper reportMapper;
    private final EvaluationMapper evaluationMapper;
    private final TicketMapper ticketMapper;

    public StaffDTO createStaff(AddStaffRequestDTO dto){
        DefaultUser user = userRepository
                .findById(dto.userId())
                .orElseThrow(() -> new EntityNotFoundException("No User with that id"));

        AbstractStaff newStaff = user.transform(dto.role().getInstance().getClass());
        userRepository.deleteById(user.getId());

        staffRepository.save(newStaff);

        return staffMapper.toDTO(newStaff);
    }

    public ReportDTO reportTeam(ReportTeamRequestDTO dto) {
        // retrieve team from db
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        // retrieve mentor from db
        Mentor mentor = (Mentor) staffRepository
                .findById(dto.mentorId())
                .orElseThrow(() -> new EntityNotFoundException("No Mentor with that id"));

        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // TODO: should check if Mentor and Team are in the same hackathon?

        DefaultReport report = new DefaultReport();
        report.setMessage(dto.message());
        report.setTeam(team);
        report.setSender(mentor);
        report.setReceiver(mentor.getHackathon());

        reportRepository.save(report);

        return reportMapper.toDTO(report);
    }

    public EvaluationDTO addEvaluation(AddEvaluationRequestDTO dto) {
        // retrieve hackathon from db
        DefaultHackathon hackathon = hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        // check if hackathon still accepts new submission
        if (hackathon.getState() != HackathonStateKind.EVALUATION.getInstance()) {
            throw new IllegalStateException("Hackathon still accepts new submission");
        }

        // retrieve submission from db
        DefaultSubmission submission = submissionRepository
                .findById(dto.submissionId())
                .orElseThrow(() -> new EntityNotFoundException("No Submission with that id"));

        // retrieve judge from db
        Judge judge = (Judge) staffRepository
                .findById(dto.judgeId())
                .orElseThrow(() -> new EntityNotFoundException("No Judge with that id"));

        // create new evaluation
        DefaultEvaluation evaluation = new DefaultEvaluation();
        evaluation.setMessage(new Score(dto.score(),dto.details()));
        evaluation.setSender(judge);
        evaluation.setReceiver(hackathon);
        evaluation.setSubmission(submission);

        // delete already existing evaluation if exist
        evaluationRepository.findBySubmission(submission.getId()).ifPresent(evaluationRepository::delete);

        // save in db
        evaluationRepository.save(evaluation);

        return evaluationMapper.toDTO(evaluation);
    }

    public void closeTicket(CloseTicketRequestDTO dto) {
        DefaultTicket ticket = ticketRepository
                .findById(dto.ticketId())
                .orElseThrow(() -> new EntityNotFoundException("No Ticket with that id"));

        ticket.setState(TicketStateKind.CLOSED);

        ticketRepository.save(ticket);
    }

    public List<TicketDTO> getTicket(GetTicketRequestDTO dto) {
        DefaultHackathon hackathon= hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        return ticketMapper
                .toDTOList(ticketRepository.findByHackathon(hackathon.getId()));
    }

    public List<EvaluationDTO> getEvaluation(GetEvaluationRequestDTO dto){
        DefaultHackathon hackathon= hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        return evaluationMapper
                .toDTOList(evaluationRepository.findByHackathon(hackathon.getId()));
    }

    public List<ReportDTO> getReport(GetReportRequestDTO dto){
        DefaultHackathon hackathon= hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        return reportMapper
                .toDTOList(reportRepository.findByHackathon(hackathon.getId()));
    }

    public void proclaimTeam(ProclaimTeamRequestDTO dto){
        DefaultTeam team = teamRepository
                .findById(dto.teamId())
                .orElseThrow(() -> new EntityNotFoundException("No Team with that id"));

        DefaultHackathon hackathon= hackathonRepository
                .findById(dto.hackathonId())
                .orElseThrow(() -> new EntityNotFoundException("No Hackathon with that id"));

        if (hackathon.getState() != HackathonStateKind.PROCLAMATION.getInstance()) {
            throw new IllegalStateException("Hackathon still accepts new evaluation");
        }

        Optional<AbstractPaymentAddress> paymentAddress = team.findAddressByKind(dto.kind());

        if(!paymentAddress.isPresent()){
            throw new PaymentException("No Payment Address found");
        }

        BigDecimal price = hackathon.getPrice();

        paymentStrategy = dto.kind().getStrategyInstance();

        if(!paymentStrategy.process(paymentAddress.get(),price)){
            throw new PaymentException("Payment Failed");
        }

        hackathon.setTeamWinner(team);

        hackathon.updateState();

        hackathonRepository.save(hackathon);
    }
}
