package com.github.ArghgrA.Hackhub.model.utente;

import lombok.Getter;

import java.util.UUID;

/**
 * Classe base che rappresenta un utente generico del sistema.
 * <p>
 * {@code AbstractUtente} contiene le informazioni fondamentali
 * di un utente, come identificatore, nome, username, email e password.
 * Questa classe viene estesa da classi più specifiche come membri dello staff
 * o partecipanti.
 * </p>
 */
@Getter
public class AbstractUtente {

    /**
     * Identificatore univoco dell'utente.
     */
    private UUID id;

    /**
     * Nome completo dell'utente.
     */
    private String nome;

    /**
     * Nome utente scelto per il login.
     */
    private String username;

    /**
     * Indirizzo email dell'utente.
     */
    private String email;

    /**
     * Password dell'utente.
     */
    private String password;
}

