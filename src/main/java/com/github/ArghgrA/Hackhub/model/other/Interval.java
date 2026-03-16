package com.github.ArghgrA.Hackhub.model.other;

import com.github.ArghgrA.Hackhub.exception.IllegalDateException;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@NoArgsConstructor @Getter
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

        validateHourPrecision(registrationStart, "registrationStart");
        validateHourPrecision(registrationEnd, "registrationEnd");
        validateHourPrecision(competitionStart, "competitionStart");
        validateHourPrecision(competitionEnd, "competitionEnd");

        if (registrationEnd.isBefore(registrationStart.plusHours(1))) {
            throw new IllegalDateException("Registration period must be at least 1 hour");
        }

        if (registrationStart.isBefore(LocalDateTime.now().plusDays(30))) {
            throw new IllegalDateException("Registration must start at least 30 days from now");
        }

        if (competitionStart.isBefore(registrationEnd)) {
            throw new IllegalDateException("Competition must start after registration ends");
        }

        if (competitionEnd.isBefore(competitionStart.plusHours(1))) {
            throw new IllegalDateException("Competition period must be at least 1 hour");
        }

        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.competitionStart = competitionStart;
        this.competitionEnd = competitionEnd;
    }

    private void validateHourPrecision(LocalDateTime date, String fieldName) {
        if (date == null) return;
        if (date.getMinute() != 0 || date.getSecond() != 0 || date.getNano() != 0) {
            throw new IllegalDateException(
                    String.format("Field '%s' must have hour precision only (got: %s)", fieldName, date)
            );
        }
    }

    public boolean inRange(LocalDateTime date) {
        return !date.isBefore(registrationStart) && !date.isAfter(competitionEnd);
    }
}