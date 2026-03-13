package com.github.ArghgrA.Hackhub.model.user.staff;

import com.github.ArghgrA.Hackhub.model.user.staff.util.StaffKind;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @Getter @Setter
@Entity
public class Judge extends AbstractStaff {
    @Override
    public String getRole() {
        return StaffKind.JUDGE.name();
    }
}
