package com.github.ArghgrA.Hackhub.model.user.staff;

import com.github.ArghgrA.Hackhub.model.user.staff.util.StaffKind;
import jakarta.persistence.Entity;

@Entity
public class Mentor extends AbstractStaff {
    @Override
    public String getRole() {
        return StaffKind.MENTOR.name();
    }
}
