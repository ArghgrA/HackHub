package com.github.ArghgrA.Hackhub.model.utente.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Rappresenta un organizzatore all'interno dello staff di un hackathon.
 * <p>
 * L' {@code Organizzatore} è un tipo specifico di {@link MembroStaff}
 * responsabile della gestione e dell'organizzazione generale dell'hackathon,
 * inclusa la pianificazione delle attività e il coordinamento dello staff.
 * </p>
 */
@Entity
@Getter
@Setter
public class Organizzatore extends MembroStaff {
    @OneToMany(mappedBy = "organizzatore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hackathon> hackathon;
}

