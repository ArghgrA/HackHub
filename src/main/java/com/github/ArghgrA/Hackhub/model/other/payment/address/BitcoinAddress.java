package com.github.ArghgrA.Hackhub.model.other.payment.address;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor
public class BitcoinAddress extends AbstractPaymentAddress {
    private String address;

    @Override
    public void addAddress(String address) {
        // TODO: add validation for bitcoin address
        this.address = address;
    }
}
