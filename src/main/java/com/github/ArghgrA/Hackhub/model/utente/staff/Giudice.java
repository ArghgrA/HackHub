package com.github.ArghgrA.Hackhub.model.utente.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Rappresenta un giudice all'interno dello staff di un hackathon.
 * <p>
 * Il {@code Giudice} è un tipo specifico di {@link MembroStaff}
 * responsabile della valutazione dei progetti partecipanti
 * secondo i criteri definiti dall'organizzazione.
 * </p>
 */
@Entity
@Table(
        name = "giudice",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_giudice_hackathon",
                columnNames = "hackathon_id"
        )
)
@Getter
@Setter
public class Giudice extends MembroStaff {
    @OneToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
}

