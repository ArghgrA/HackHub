package com.github.ArghgrA.Hackhub.model.other;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

//@NoArgsConstructor @Getter @Setter
//@Entity @Table(name = "table_interval")
@Embeddable
@NoArgsConstructor
public class Interval {

    private LocalDateTime registrationStart;

    private LocalDateTime registrationEnd;

    private LocalDateTime competitionStart;

    private LocalDateTime competitionEnd;

    public Interval(
            LocalDateTime registrationStart,
            LocalDateTime registrationEnd,
            LocalDateTime competitionStart,
            LocalDateTime competitionEnd) {

        if (registrationEnd.isBefore(registrationStart.plusHours(1))) {
            throw new IllegalArgumentException("Registration period must be at least 1 hour");
        }

        if (registrationStart.isBefore(LocalDateTime.now().plusDays(30))) {
            throw new IllegalArgumentException("Registration must start at least 30 days from now");
        }

        if (competitionStart.isBefore(registrationEnd)) {
            throw new IllegalArgumentException("Competition must start after registration ends");
        }

        if (competitionEnd.isBefore(competitionStart.plusHours(1))) {
            throw new IllegalArgumentException("Competition period must be at least 1 hour");
        }

        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.competitionStart = competitionStart;
        this.competitionEnd = competitionEnd;
    }
}