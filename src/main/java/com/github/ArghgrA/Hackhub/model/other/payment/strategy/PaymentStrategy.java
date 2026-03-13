package com.github.ArghgrA.Hackhub.model.other.payment.strategy;

import com.github.ArghgrA.Hackhub.model.other.payment.address.PaymentAddress;

import java.math.BigDecimal;

public sealed interface PaymentStrategy permits BitcoinStrategy,CardStrategy {
    boolean process(PaymentAddress address, BigDecimal amount);
}
