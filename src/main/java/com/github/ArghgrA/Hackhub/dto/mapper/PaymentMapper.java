package com.github.ArghgrA.Hackhub.dto.mapper;

import com.github.ArghgrA.Hackhub.dto.model.PaymentDTO;
import com.github.ArghgrA.Hackhub.model.other.payment.address.AbstractPaymentAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "team.id",target = "teamId")
    PaymentDTO toDTO(AbstractPaymentAddress address);
    List<PaymentDTO> toDTOList(List<AbstractPaymentAddress> addresses);
}
