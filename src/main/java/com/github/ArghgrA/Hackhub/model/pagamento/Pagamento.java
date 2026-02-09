package com.github.ArghgrA.Hackhub.model.pagamento;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.team.Team;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//@Embeddable
/*public interface Pagamento {
}*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@Getter
public abstract class Pagamento{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PagamentoKind pagamentokind;


    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}


