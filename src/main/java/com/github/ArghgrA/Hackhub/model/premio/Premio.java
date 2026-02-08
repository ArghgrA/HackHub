package com.github.ArghgrA.Hackhub.model.premio;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import com.github.ArghgrA.Hackhub.model.pagamento.PagamentoKind;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


/*public interface Premio {
    void pay(Pagamento pagamento);
}
 */

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Setter
@NoArgsConstructor
public abstract class Premio{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int somma;

    @OneToOne
    @JoinColumn(name = "hackathon_id")
    private Hackathon hackathon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PremioKind premioKind;

    public Premio(int somma){
        if(somma <=0) throw new IllegalArgumentException("");
        this.somma = somma;
    }


    //Viene implementato dalle sottoclasi
     abstract void pay(Pagamento pagamento);
}
