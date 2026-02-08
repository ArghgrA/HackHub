package com.github.ArghgrA.Hackhub.model.premio;

import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class CartaCreditoPremio extends Premio{

    public CartaCreditoPremio(int somma){
        super(somma);
    }

    @Override
    public void pay(Pagamento pagamento) {

    }
}
