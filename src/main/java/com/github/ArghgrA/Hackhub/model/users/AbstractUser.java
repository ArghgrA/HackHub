package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.abstractions.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity @Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class AbstractUser implements User<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String username;

    private String email;

    private String password;
}
