package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.exceptions.IntervalloInvalidoException;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
/**
 * Rappresenta un intervallo temporale composto da quattro istanti fondamentali:
 * - inizio e fine del periodo di iscrizione,
 * - inizio e fine della competizione.
 * <p>
 * Questa classe è immutabile e garantisce, tramite il costruttore, che i seguenti vincoli siano rispettati:
 * <ol>
 *   <li>La fine delle iscrizioni deve avvenire almeno un'ora dopo l'inizio delle iscrizioni.</li>
 *   <li>L'inizio delle iscrizioni deve avvenire almeno 30 giorni a partire dal momento corrente.</li>
 *   <li>L'inizio della competizione non deve avvenire prima della fine delle iscrizioni.</li>
 *   <li>La fine della competizione deve avvenire almeno un'ora dopo l'inizio della competizione.</li>
 * </ol>
 * Se uno qualsiasi di questi vincoli non è soddisfatto, il costruttore lancia {@link IntervalloInvalidoException}.
 */
@Getter
public class Intervallo {

    /**
     * Data e ora di inizio del periodo di iscrizione.
     */
    private final LocalDateTime inizioIscrizioni;

    /**
     * Data e ora di fine del periodo di iscrizione.
     */
    private final LocalDateTime fineIscrizioni;

    /**
     * Data e ora di inizio della competizione.
     */
    private final LocalDateTime inizioCompetizione;

    /**
     * Data e ora di fine della competizione.
     */
    private final LocalDateTime fineCompetizione;

    /**
     * Costruisce un nuovo oggetto {@code Intervallo} con i quattro istanti temporali specificati.
     * <p>
     * Tutti i parametri devono essere non nulli. Inoltre, devono rispettare i vincoli descritti nella documentazione della classe.
     *
     * @param inizioIscrizioni     data e ora di inizio delle iscrizioni (non null)
     * @param fineIscrizioni       data e ora di fine delle iscrizioni (non null)
     * @param inizioCompetizione   data e ora di inizio della competizione (non null)
     * @param fineCompetizione     data e ora di fine della competizione (non null)
     * @throws IntervalloInvalidoException se uno dei vincoli temporali non è rispettato
     * @throws NullPointerException        se uno qualsiasi dei parametri è null
     */
    public Intervallo(
            @NonNull LocalDateTime inizioIscrizioni,
            @NonNull LocalDateTime fineIscrizioni,
            @NonNull LocalDateTime inizioCompetizione,
            @NonNull LocalDateTime fineCompetizione) {

        // la durata minima del periodo di iscrizione è di 1 ora
        if (fineIscrizioni.isBefore(inizioIscrizioni.plusHours(1))) {
            throw new IntervalloInvalidoException("La fine delle iscrizioni deve essere almeno 1 ora dopo l'inizio.");
        }

        // le iscrizioni devono iniziare tra almeno 30 giorni
        if (inizioIscrizioni.isBefore(LocalDateTime.now().plusDays(30))) {
            throw new IntervalloInvalidoException("L'inizio delle iscrizioni deve avvenire tra almeno 30 giorni.");
        }

        // la competizione non può iniziare prima della chiusura delle iscrizioni
        if (inizioCompetizione.isBefore(fineIscrizioni)) {
            throw new IntervalloInvalidoException("L'inizio della competizione non può precedere la fine delle iscrizioni.");
        }

        // la competizione deve durare almeno 1 ora
        if (fineCompetizione.isBefore(inizioCompetizione.plusHours(1))) {
            throw new IntervalloInvalidoException("La fine della competizione deve essere almeno 1 ora dopo l'inizio.");
        }

        this.inizioIscrizioni = inizioIscrizioni;
        this.fineIscrizioni = fineIscrizioni;
        this.inizioCompetizione = inizioCompetizione;
        this.fineCompetizione = fineCompetizione;
    }
}