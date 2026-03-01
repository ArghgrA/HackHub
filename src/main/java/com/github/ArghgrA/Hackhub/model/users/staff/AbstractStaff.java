package com.github.ArghgrA.Hackhub.model.users.staff;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.abstractions.Staff;
import com.github.ArghgrA.Hackhub.model.users.AbstractUser;
import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class AbstractStaff extends AbstractUser implements Staff<UUID, Number> {
    Hackathon<UUID,Number> hackathon;
}
