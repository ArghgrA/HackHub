package com.github.ArghgrA.Hackhub.model.users;


import com.github.ArghgrA.Hackhub.model.other.invites.DefaultInvite;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class DefaultUser extends AbstractUser {
    @OneToMany(mappedBy = "id")
    List<DefaultInvite> invites;
}
