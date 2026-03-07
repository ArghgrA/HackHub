package com.github.ArghgrA.Hackhub.model.hackathon.state.util;

import com.github.ArghgrA.Hackhub.model.hackathon.state.HackathonState;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HackathonStateConverter implements AttributeConverter<HackathonState,String> {
    @Override
    public String convertToDatabaseColumn(HackathonState state) {
        return HackathonStateEnum.fromState(state).name();
    }

    @Override
    public HackathonState convertToEntityAttribute(String s) {
        return HackathonStateEnum.valueOf(s).getInstance();
    }
}
