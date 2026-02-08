package com.github.ArghgrA.Hackhub.model.hackathon;
import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.utente.staff.Giudice;
import com.github.ArghgrA.Hackhub.model.utente.staff.Mentore;
import com.github.ArghgrA.Hackhub.model.premio.Premio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Rappresenta un Hackathon all'interno del dominio dell'applicazione.
 * <p>
 * Questa classe contiene tutte le informazioni principali relative
 * a un hackathon, incluse le caratteristiche generali, la posizione,
 * il premio, il periodo di svolgimento e lo staff coinvolto.
 * </p>
 *
 * <p>
 * Ogni hackathon è identificato univocamente tramite un {@link UUID}.
 * Alcuni attributi, come il giudice e i mentori, possono essere
 * modificati anche dopo la creazione dell'oggetto.
 * </p>
 */

@Getter
@Setter
public class Hackathon {

    /**
     * Identificatore univoco dell'hackathon.
     */
    private UUID id;

    /**
     * Nome dell'hackathon.
     */
    private String nome;

    /**
     * Regolamento ufficiale dell'hackathon.
     */
    private String regolamento;

    /**
     * Posizione in cui si svolge l'hackathon.
     */
    private Posizione posizione;

    /**
     * Premio associato all'hackathon.
     */
    private Premio premio;

    /**
     * Intervallo temporale di svolgimento dell'hackathon.
     */
    private Intervallo intervallo;

    /**
     * Numero massimo di membri consentiti per ciascun team.
     */
    private int maxNumMebri;

    /**
     * Giudice assegnato all'hackathon.
     * <p>
     * Può essere impostato o modificato dopo la creazione dell'istanza.
     * </p>
     */
    @Setter(AccessLevel.NONE)
    private Giudice giudice;

    /**
     * Lista dei mentori associati all'hackathon.
     * <p>
     * Può essere aggiornata dopo la creazione dell'oggetto.
     * </p>
     */
    @Setter(AccessLevel.NONE)
    private List<Mentore> mentori;

    public Hackathon() {
        id = UUID.randomUUID();
        this.mentori = new ArrayList<Mentore>();
    }

    public void setMentore(Mentore mentore) {
        mentori.add(mentore);
    }
}

