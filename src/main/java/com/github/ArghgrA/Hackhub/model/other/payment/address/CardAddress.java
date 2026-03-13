package com.github.ArghgrA.Hackhub.model.other.payment.address;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class CardAddress extends AbstractPaymentAddress {
    private String iban;

    @Override
    public void addAddress(String address) {
        // TODO: add validation for card iban
        this.iban = address;
    }
}
