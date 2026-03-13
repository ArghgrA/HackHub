package com.github.ArghgrA.Hackhub.model.user.staff;

import com.github.ArghgrA.Hackhub.model.user.staff.util.StaffKind;
import jakarta.persistence.Entity;

@Entity
public class Organizer extends AbstractStaff {
    @Override
    public String getRole() {
        return StaffKind.ORGANIZER.name();
    }
}