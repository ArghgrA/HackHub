package com.github.ArghgrA.Hackhub.model;

import com.github.ArghgrA.Hackhub.model.team.Team;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

/**
 * Rappresenta un invito inviato a un utente per partecipare a un team.
 * <p>
 * Ogni invito è identificato da un ID univoco generato automaticamente e contiene
 * i riferimenti all'utente destinatario, al team che ha inviato l'invito e un messaggio testuale opzionale.
 */
@Getter
public class Invito {
    /**
     * Identificatore univoco dell'invito, generato automaticamente al momento della creazione.
     */
    private UUID id;

    /**
     * Identificatore del team che ha inviato l'invito.
     */

    private UUID idTeam;

    /**
     * Identificatore dell'utente a cui è rivolto l'invito.
     */
    private UUID idUtente;

    /**
     * Messaggio testuale associato all'invito (può contenere informazioni aggiuntive o personalizzate).
     */
    private String testo;

    /**
     * Costruisce un nuovo invito con i parametri specificati.
     * L'ID dell'invito viene generato automaticamente tramite {@link UUID#randomUUID()}.
     *
     * @param idTeam   identificatore del team che invia l'invito (non null)
     * @param idUtente identificatore dell'utente destinatario (non null)
     * @param testo    messaggio testuale dell'invito (se null allora stringa vuota)
     */
    public Invito(@NonNull  UUID idTeam, @NonNull UUID idUtente, String testo) {
        this.id = UUID.randomUUID();
        this.idTeam = idTeam;
        this.idUtente = idUtente;
        this.testo = (testo == null ? "" : testo);
    }
}