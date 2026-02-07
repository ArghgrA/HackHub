package com.github.ArghgrA.Hackhub.model.pagamento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class CryptoPagamento implements Pagamento{
    private final String walletAddress;
}
