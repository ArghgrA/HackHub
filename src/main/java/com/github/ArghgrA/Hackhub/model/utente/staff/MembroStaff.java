package com.github.ArghgrA.Hackhub.model.utente.staff;

import com.github.ArghgrA.Hackhub.model.utente.AbstractUtente;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Rappresenta un membro dello staff di un hackathon.
 * <p>
 * Questa classe estende {@link AbstractUtente} e modella
 * un utente che ricopre un ruolo organizzativo o di supporto
 * all'interno di un hackathon (ad esempio giudici o mentori).
 * </p>
 *
 * <p>
 * Ogni membro dello staff è associato a uno specifico hackathon
 * tramite il suo identificatore.
 * </p>
 */
@Getter @Setter
public class MembroStaff extends AbstractUtente {

    /**
     * Identificatore dell'hackathon a cui il membro dello staff è associato.
     */
    private UUID idHackathon;
}

