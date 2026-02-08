package com.github.ArghgrA.Hackhub.model.utente.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Rappresenta un mentore all'interno dello staff di un hackathon.
 * <p>
 * Il {@code Mentore} è un tipo specifico di {@link MembroStaff}
 * che fornisce supporto, guida e consigli ai partecipanti
 * durante lo svolgimento dell'hackathon.
 * </p>
 */
@Entity
@Getter
@Setter
public class Mentore extends MembroStaff {
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
}

