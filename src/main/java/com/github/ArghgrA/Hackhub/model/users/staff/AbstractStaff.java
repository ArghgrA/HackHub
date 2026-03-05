package com.github.ArghgrA.Hackhub.model.users.staff;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.abstractions.Staff;
import com.github.ArghgrA.Hackhub.model.abstractions.User;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.users.AbstractUser;
import com.github.ArghgrA.Hackhub.model.users.DefaultUser;
import com.github.ArghgrA.Hackhub.model.users.TeamMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.function.UnaryOperator;

@Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractStaff extends AbstractUser implements Staff<UUID,AbstractHackathon> {
    @ManyToOne @JoinColumn(name = "hackathon_id")
    AbstractHackathon hackathon;

    @Override @SuppressWarnings("all")
    public <T extends AbstractUser> T prototype(Class<T> type) {
        UnaryOperator<AbstractUser> copyFields = source -> {
            source.setName(super.getName());
            source.setUsername(super.getUsername());
            source.setEmail(super.getEmail());
            source.setPassword(super.getPassword());
            return source;
        };

        return switch (type) {
            case Class<?> o when o == DefaultUser.class -> (T) copyFields.apply(new DefaultUser());
            default -> throw new IllegalArgumentException("Type not known: " + type);
        };
    }
}
