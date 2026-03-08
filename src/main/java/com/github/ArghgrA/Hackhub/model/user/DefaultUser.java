package com.github.ArghgrA.Hackhub.model.user;


import com.github.ArghgrA.Hackhub.model.other.message.DefaultInvite;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor @Getter @Setter
@Entity
public class DefaultUser extends AbstractUser {
    @OneToMany(mappedBy = "id")
    private List<DefaultInvite> invites;
}
