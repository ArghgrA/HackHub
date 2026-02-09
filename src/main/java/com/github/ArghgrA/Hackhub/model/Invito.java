package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.team.Team;
import com.github.ArghgrA.Hackhub.model.utente.AbstractUtente;
import com.github.ArghgrA.Hackhub.model.utente.DefaultUtente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.UUID;

/**
 * Rappresenta un invito inviato a un utente per partecipare a un team.
 * <p>
 * Ogni invito è identificato da un ID univoco generato automaticamente e contiene
 * i riferimenti all'utente destinatario, al team che ha inviato l'invito e un messaggio testuale opzionale.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invito {
    /**
     * Identificatore univoco dell'invito, generato automaticamente al momento della creazione.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Identificatore del team che ha inviato l'invito.
     */
    @OneToOne
    @JoinColumn(name= "team_id")
    private Team idTeam;

    /**
     * Identificatore dell'utente a cui è rivolto l'invito.
     */
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private DefaultUtente utenteInvitato;

    /**
     * Messaggio testuale associato all'invito (può contenere informazioni aggiuntive o personalizzate).
     */
    private String testo;

    /**
     * Costruisce un nuovo invito con i parametri specificati.
     * L'ID dell'invito viene generato automaticamente tramite {@link UUID#randomUUID()}.
     *
     * @param idTeam   identificatore del team che invia l'invito (non null)
     * @param utenteInvitato identificatore dell'utente destinatario (non null)
     * @param testo    messaggio testuale dell'invito (se null allora stringa vuota)
     */
    public Invito(@NonNull  Team idTeam, @NonNull DefaultUtente utenteInvitato, String testo) {
        this.id = UUID.randomUUID();
        this.idTeam = idTeam;
        this.utenteInvitato = utenteInvitato;
        this.testo = (testo == null ? "" : testo);
    }
}