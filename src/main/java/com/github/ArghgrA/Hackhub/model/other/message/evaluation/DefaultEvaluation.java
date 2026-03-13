package com.github.ArghgrA.Hackhub.model.other.message.evaluation;


import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.other.message.AbstractMessage;
import com.github.ArghgrA.Hackhub.model.other.message.DefaultSubmission;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class DefaultEvaluation extends AbstractMessage<Score,Judge,AbstractHackathon> {
    @ManyToOne
    @JoinColumn(name = "judge_id")
    private Judge sender;

    @ManyToOne @JoinColumn(name = "hackathon_id")
    private AbstractHackathon receiver;

    @OneToOne @JoinColumn(name = "submission_id")
    private DefaultSubmission submission;

    @Embedded
    private Score message;
}
