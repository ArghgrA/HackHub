package com.github.ArghgrA.Hackhub.model.utente;

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
public class MembroTeam extends AbstractUtente {

    /**
     * Identificatore del team a cui il membro appartiene.
     */
    private UUID idTeam;
}

