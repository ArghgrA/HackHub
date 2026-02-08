package com.github.ArghgrA.Hackhub.model.utente;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

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

/*
Se in futuro volessimo andare a far diventare questa classe in entita' dovremmo fare cosi:
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // oppure SINGLE_TABLE o TABLE_PER_CLASS
 */
@Getter
//@MappedSuperclass
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractUtente {

    /**
     * Identificatore univoco dell'utente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

