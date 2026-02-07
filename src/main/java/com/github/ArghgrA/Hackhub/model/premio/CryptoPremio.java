package com.github.ArghgrA.Hackhub.model.premio;

import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class CryptoPremio implements Premio{
    private final int somma;

    @Override
    public void pay(Pagamento pagamento) {

    }
}
