package com.github.ArghgrA.Hackhub.model.user.staff.util;

import com.github.ArghgrA.Hackhub.model.user.staff.AbstractStaff;
import com.github.ArghgrA.Hackhub.model.user.staff.Judge;
import com.github.ArghgrA.Hackhub.model.user.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.user.staff.Organizer;

public enum StaffEnum {
    ORGANIZER,
    JUDGE,
    MENTOR;

    @SuppressWarnings("unchecked")
    public <T extends AbstractStaff> T getInstance(){
        return (T) switch (this){
            case ORGANIZER -> new Organizer();
            case JUDGE -> new Judge();
            case MENTOR -> new Mentor();
        };
    }
}
