package com.github.ArghgrA.Hackhub.model.user.staff;

import com.github.ArghgrA.Hackhub.model.abstraction.Staff;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.user.AbstractUser;
import com.github.ArghgrA.Hackhub.model.user.DefaultUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.function.UnaryOperator;

@Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractStaff extends AbstractUser implements Staff<UUID,AbstractHackathon> {
    @ManyToOne @JoinColumn(name = "hackathon_id")
    AbstractHackathon hackathon;

    @Override @SuppressWarnings("all")
    public <T extends AbstractUser> T transform(Class<T> type) {
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
