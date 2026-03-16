package com.github.ArghgrA.Hackhub.model.other.message.call.facade;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalendarConfig {
    @PostConstruct
    public void initCalendarFacade() {
        CalendarFacade.setCalendarService(new GoogleCalendarService());
    }
}
