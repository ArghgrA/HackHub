package com.github.ArghgrA.Hackhub.model.other.payment.strategy;

import com.github.ArghgrA.Hackhub.model.other.payment.address.PaymentAddress;

import java.math.BigDecimal;

public final class CardStrategy implements PaymentStrategy{
    @Override
    public boolean process(PaymentAddress address, BigDecimal amount) {
        // TODO: implement payment processing for card with external system
        return true;
    }
}
