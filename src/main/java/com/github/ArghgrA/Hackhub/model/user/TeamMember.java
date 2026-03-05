package com.github.ArghgrA.Hackhub.model.user;

import com.github.ArghgrA.Hackhub.model.team.DefaultTeam;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.UnaryOperator;

@NoArgsConstructor @Getter @Setter
@Entity
public class TeamMember extends AbstractUser {
    @ManyToOne @JoinColumn(name = "team_id")
    private DefaultTeam team;

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
