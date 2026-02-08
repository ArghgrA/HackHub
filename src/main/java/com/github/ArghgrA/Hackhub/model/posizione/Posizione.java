package com.github.ArghgrA.Hackhub.model.posizione;

/*public interface Posizione {
} */

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.pagamento.PagamentoKind;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.UUID;
@Entity
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Posizione{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PosizioneKind posizioneKind;

    @OneToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;
}
