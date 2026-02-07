package com.github.ArghgrA.Hackhub.model.utente;

import com.github.ArghgrA.Hackhub.model.Invito;
import lombok.Getter;

import java.util.List;

/**
 * Rappresenta un utente standard del sistema.
 * <p>
 * {@code DefaultUtente} estende {@link AbstractUtente} e aggiunge
 * funzionalità specifiche per gli utenti generici, come la gestione
 * degli inviti ricevuti.
 * </p>
 */
@Getter
public class DefaultUtente extends AbstractUtente {

    /**
     * Lista degli inviti ricevuti dall'utente.
     */
    private List<Invito> inviti;
}

