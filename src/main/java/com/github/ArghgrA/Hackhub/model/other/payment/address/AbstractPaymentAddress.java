package com.github.ArghgrA.Hackhub.model.other.payment.address;

import com.github.ArghgrA.Hackhub.model.team.AbstractTeam;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor @Getter @Setter
public abstract class AbstractPaymentAddress implements PaymentAddress{
    @Id @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne @JoinColumn(name = "team_id")
    private AbstractTeam team;
}
