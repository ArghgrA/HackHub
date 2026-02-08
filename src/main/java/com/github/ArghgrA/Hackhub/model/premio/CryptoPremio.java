package com.github.ArghgrA.Hackhub.model.premio;

import com.github.ArghgrA.Hackhub.model.hackathon.Hackathon;
import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@Entity
@NoArgsConstructor
public class CryptoPremio extends Premio{

    public CryptoPremio(int somma) {
       super(somma);
    }

    @Override
    void pay(Pagamento pagamento) {

    }
}
