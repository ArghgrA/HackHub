package com.github.ArghgrA.Hackhub.model.users.staff;

import com.github.ArghgrA.Hackhub.model.abstractions.Hackathon;
import com.github.ArghgrA.Hackhub.model.abstractions.Staff;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.users.AbstractUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class AbstractStaff<H extends Hackathon<UUID>> extends AbstractUser implements Staff<UUID,H> {
    @ManyToOne @JoinColumn(name = "hackathon_id")
    H hackathon;
}
