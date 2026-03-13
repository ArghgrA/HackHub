package com.github.ArghgrA.Hackhub.repository;

import com.github.ArghgrA.Hackhub.model.other.payment.address.AbstractPaymentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository<T extends AbstractPaymentAddress> extends JpaRepository<T, UUID> {
}
