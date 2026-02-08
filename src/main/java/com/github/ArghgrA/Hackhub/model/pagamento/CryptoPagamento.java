package com.github.ArghgrA.Hackhub.model.pagamento;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class CryptoPagamento extends Pagamento{

    private String walletAddress;
}