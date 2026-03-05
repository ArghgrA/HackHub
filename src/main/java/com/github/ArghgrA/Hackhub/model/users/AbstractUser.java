package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.abstractions.User;
import com.github.ArghgrA.Hackhub.model.users.staff.Judge;
import com.github.ArghgrA.Hackhub.model.users.staff.Mentor;
import com.github.ArghgrA.Hackhub.model.users.staff.Organizer;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.function.UnaryOperator;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @ToString @NoArgsConstructor
public abstract class AbstractUser implements User<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String username;

    private String email;

    private String password;

    @SuppressWarnings("all")
    public <T extends AbstractUser> T prototype(Class<T> type) {
        UnaryOperator<AbstractUser> copyFields = source -> {
            source.setName(this.name);
            source.setUsername(this.username);
            source.setEmail(this.email);
            source.setPassword(this.password);
            return source;
        };

        return switch (type) {
            case Class<?> c when c == Organizer.class -> (T) copyFields.apply(new Organizer());
            case Class<?> c when c == Judge.class -> (T) copyFields.apply(new Judge());
            case Class<?> c when c == Mentor.class -> (T) copyFields.apply(new Mentor());
            case Class<?> c when c == TeamMember.class -> (T) copyFields.apply(new TeamMember());
            default -> throw new IllegalArgumentException("Type not known: " + type);
        };
    }
}
