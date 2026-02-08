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
@Entity
public class Hackathon {

    /**
     * Identificatore univoco dell'hackathon.
     */
    @Id
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
    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Posizione posizione;

    /**
     * Premio associato all'hackathon.
     */
    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Premio premio;

    /**
     * Intervallo temporale di svolgimento dell'hackathon.
     */
    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
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

    //Va ad aggiungere una colonna chiamata giudice_id con id del giudice preso dalla classe Giudice
    //@JoinColumn(name = "giudice_id", referencedColumnName = "id")
    //@OneToOne(mappedBy = "hackathon")
    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    //@OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private Giudice giudice;

    /**
     * Lista dei mentori associati all'hackathon.
     * <p>
     * Può essere aggiornata dopo la creazione dell'oggetto.
     * </p>
     */
    @Setter(AccessLevel.NONE)
    /*
    mappedBy -> indica che la relazione e' gestita dall'entita' Mentore
    cascade -> le operazioni a cascata se salviamo/cancelliamo l'hackathon automaticamente viene salvato anche il mentore
    orphanRemoval -> se rimuoviamo un mentore dalla lista mentori dell'hackathon, quel mentore viene elimanto dal db.
     */
    @OneToMany(mappedBy = "hackathon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mentore> mentori;

    public Hackathon() {
        id = UUID.randomUUID();
        //this.giudice = new ArrayList<Giudice>();
        this.mentori = new ArrayList<Mentore>();
    }

    public void setMentore(Mentore mentore) {
        mentori.add(mentore);
    }
}

