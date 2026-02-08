package com.github.ArghgrA.Hackhub.model.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class CartaCreditoPagamento implements Pagamento{
    private final String iban;

}
