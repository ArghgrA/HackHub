package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.abstractions.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractUser implements User<UUID> {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;

    String username;

    String email;

    String password;
}
