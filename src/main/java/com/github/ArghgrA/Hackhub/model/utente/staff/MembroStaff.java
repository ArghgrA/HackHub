package com.github.ArghgrA.Hackhub.model.utente.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.utente.AbstractUtente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Rappresenta un membro dello staff di un hackathon.
 * <p>
 * Questa classe estende {@link AbstractUtente} e modella
 * un utente che ricopre un ruolo organizzativo o di supporto
 * all'interno di un hackathon (ad esempio giudici o mentori).
 * </p>
 *
 * <p>
 * Ogni membro dello staff è associato a uno specifico hackathon
 * tramite il suo identificatore.
 * </p>
 */

/*
Se in futuro volessimo andare a far diventare questa classe in entita' dovremmo fare cosi:
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // opzionale se vuoi separare le tabelle
 */


/*
Se invece volissimo andare a fare con nel vp dobbiamo fare questa cosa:
@Inheritance(strategy = InheritanceType.JOINED)

@ManyToOne
@JoinColumn(name = "hackathon_id")
private Hackathon hackathon;

cosi che le sottoclassi non abbiano idHackathon ripetuto, il problema e che in giudice dobbiamo andare
a inserire questa clausola:

@Table(
    name = "giudice",
    uniqueConstraints = @UniqueConstraint(columnNames = "hackathon_id")
)

@Table(name = "giudice") → significa che la classe sarà mappata sulla tabella giudice
uniqueConstraints = @UniqueConstraint(columnNames = "hackathon_id"):
questo impone un vincolo UNICO sulla colonna hackathon_id cio' vuole dire che ogni Hackathon può avere al massimo un Giudice.
 */

@Getter @Setter
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.JOINED)
public class MembroStaff extends AbstractUtente {
    /**
     * Identificatore dell'hackathon a cui il membro dello staff è associato.
     */
    //Per ora la leviamo pk idHackathon sta come fk nelle classi Giudice e Mentore, come giusto che sia nelle regole delle
    //basi di analisi
    //private UUID idHackathon;
    /**
     * Hackathon a cui il membro dello staff appartiene.
     */
    /*
    @ManyToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
     */
}

