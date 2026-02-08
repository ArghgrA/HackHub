package com.github.ArghgrA.Hackhub.model.utente;

import com.github.ArghgrA.Hackhub.model.team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.util.UUID;

/**
 * Rappresenta un membro di un team all'interno del sistema.
 * <p>
 * {@code MembroTeam} estende {@link AbstractUtente} e aggiunge
 * l'associazione a un team specifico tramite il suo identificatore.
 * </p>
 */
@Getter
@Entity
public class MembroTeam extends AbstractUtente {

    /**
     * Identificatore del team a cui il membro appartiene.
     */
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}

