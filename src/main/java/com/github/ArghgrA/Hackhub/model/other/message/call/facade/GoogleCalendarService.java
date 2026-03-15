package com.github.ArghgrA.Hackhub.model.other.message.call.facade;

import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;

import java.util.Optional;
import java.util.UUID;

public class GoogleCalendarService implements CalendarService{
    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public Optional<String> createCalendarEvent(DefaultCall call) {
        try {
            Thread.sleep(300); // Simula latenza
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String eventId = "google_cal_event_" + UUID.randomUUID();
        return Optional.of(eventId);
    }
}
