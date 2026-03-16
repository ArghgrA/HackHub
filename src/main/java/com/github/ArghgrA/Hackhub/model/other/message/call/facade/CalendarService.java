package com.github.ArghgrA.Hackhub.model.other.message.call.facade;

import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;

import java.util.Optional;

public interface CalendarService {
    boolean isAvailable();
    Optional<String> createCalendarEvent(DefaultCall call);
}
