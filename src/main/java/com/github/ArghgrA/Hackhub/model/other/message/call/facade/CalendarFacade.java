package com.github.ArghgrA.Hackhub.model.other.message.call.facade;

import com.github.ArghgrA.Hackhub.model.other.message.call.DefaultCall;
import lombok.Getter;
import lombok.Setter;


public class CalendarFacade {
    @Setter
    private static CalendarService calendarService;
    @Getter
    private static final CalendarFacade INSTANCE = new CalendarFacade();

    public static String acceptCall(DefaultCall call) {
        if (calendarService == null) {
            throw new IllegalStateException("CalendarService not initialized");
        }
        if (!calendarService.isAvailable()) {
            throw new IllegalStateException("Calendar service is currently unavailable");
        }
        return calendarService.createCalendarEvent(call)
                .orElseThrow(() -> new IllegalStateException("Failed to create calendar event"));
    }

}
