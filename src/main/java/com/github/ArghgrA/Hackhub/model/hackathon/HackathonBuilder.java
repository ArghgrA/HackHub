package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.premio.Premio;
import com.github.ArghgrA.Hackhub.model.utente.staff.Giudice;
import com.github.ArghgrA.Hackhub.model.utente.staff.Mentore;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Builder per la creazione di istanze di {@link Hackathon}.
 * <p>
 * Questa classe implementa il pattern Builder e consente di costruire
 * un oggetto {@code Hackathon} in modo graduale, rendendo più chiara
 * e sicura l’inizializzazione dei suoi attributi.
 * </p>
 *
 * <p>
 * Alcuni campi non sono modificabili direttamente tramite setter
 * (come {@code id}, {@code giudice} e {@code mentori}) per garantire
 * la coerenza dell’oggetto finale.
 * </p>
 */
@Setter
public class HackathonBuilder {

    /**
     * Identificatore univoco dell'hackathon.
     * Viene generato automaticamente al momento della creazione
     * dell'oggetto {@link Hackathon}.
     */
    @Setter(AccessLevel.NONE)
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
     * Deve essere maggiore di 1.
     */
    private int maxNumMebri;

    /**
     * Giudice associato all'hackathon.
     * Non può essere impostato direttamente tramite il builder.
     */
    @Setter(AccessLevel.NONE)
    private Giudice giudice;

    /**
     * Lista dei mentori associati all'hackathon.
     * Viene inizializzata automaticamente come lista vuota.
     */
    @Setter(AccessLevel.NONE)
    private List<Mentore> mentori;

    /**
     * Imposta il numero massimo di membri per team.
     *
     * @param maxNumMebri numero massimo di membri (deve essere > 1)
     * @throws IllegalArgumentException se il valore è minore o uguale a 1
     */
    public void setMaxNumMebri(int maxNumMebri) {
        if (maxNumMebri <= 1) {
            throw new IllegalArgumentException(
                    "Il numero massimo di membri deve essere maggiore di 1"
            );
        }
        this.maxNumMebri = maxNumMebri;
    }

    /**
     * Costruisce e restituisce una nuova istanza di {@link Hackathon}
     * utilizzando i valori impostati nel builder.
     *
     * <p>
     * L'identificatore viene generato automaticamente,
     * il giudice viene inizializzato a {@code null}
     * e la lista dei mentori viene creata vuota.
     * </p>
     *
     * @return nuova istanza di {@link Hackathon}
     */
    public Hackathon getResult() {
        return new Hackathon(
                UUID.randomUUID(),
                nome,
                regolamento,
                posizione,
                premio,
                intervallo,
                maxNumMebri,
                null,
                new ArrayList<Mentore>()
        );
    }
}

