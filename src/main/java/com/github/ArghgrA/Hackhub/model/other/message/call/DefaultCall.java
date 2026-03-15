package com.github.ArghgrA.Hackhub.model.other.message.call;

import com.github.ArghgrA.Hackhub.model.other.message.AbstractMessage;
import com.github.ArghgrA.Hackhub.model.other.message.ticket.DefaultTicket;
import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor @Getter @Setter
public class DefaultCall extends AbstractMessage<LocalDateTime, Mentor, AbstractTeam> {

    @ManyToOne @JoinColumn(name = "mentor_id")
    private Mentor sender;

    @ManyToOne @JoinColumn(name = "team_id")
    private AbstractTeam receiver;

    @OneToOne @JoinColumn(name = "ticket_id")
    private DefaultTicket ticket;

    private LocalDateTime message;

    // memorizza l'ID dell'evento Google Calendar
    private String calendarEventId;
}
