package com.github.ArghgrA.Hackhub.model.users;

import com.github.ArghgrA.Hackhub.model.abstractions.User;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class AbstractUser implements User<UUID> {
    UUID id;
    String name;
    String username;
    String email;
    String password;
}
