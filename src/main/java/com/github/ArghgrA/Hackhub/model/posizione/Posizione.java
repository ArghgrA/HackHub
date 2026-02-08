package com.github.ArghgrA.Hackhub.model.posizione;

/*public interface Posizione {
} */

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import jakarta.persistence.*;

import java.util.UUID;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Posizione{
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
}
