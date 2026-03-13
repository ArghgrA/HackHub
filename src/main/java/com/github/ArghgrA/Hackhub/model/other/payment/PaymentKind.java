package com.github.ArghgrA.Hackhub.model.other.payment;

import com.github.ArghgrA.Hackhub.model.other.payment.address.BitcoinAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.address.CardAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.address.PaymentAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.strategy.BitcoinStrategy;
import com.github.ArghgrA.Hackhub.model.other.payment.strategy.CardStrategy;
import com.github.ArghgrA.Hackhub.model.other.payment.strategy.PaymentStrategy;

public enum PaymentKind {
    CARD,
    BITCOIN;

    @SuppressWarnings("unchecked")
    public <T extends PaymentAddress> T getAddressInstance() {
        return (T) switch (this) {
            case CARD -> new CardAddress();
            case BITCOIN -> new BitcoinAddress();
        };
    }

    @SuppressWarnings("unchecked")
    public <T extends PaymentStrategy> T getStrategyInstance() {
        return (T) switch (this) {
            case CARD -> new CardStrategy();
            case BITCOIN -> new BitcoinStrategy();
        };
    }

    public static boolean areCompatible(PaymentStrategy strategy, PaymentAddress address) {
        return switch (strategy) {
            case CardStrategy s -> address instanceof CardAddress;
            case BitcoinStrategy s -> address instanceof BitcoinAddress;
        };
    }
}
