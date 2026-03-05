package com.github.ArghgrA.Hackhub.model.users;


import com.github.ArghgrA.Hackhub.model.other.invites.DefaultInvite;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor @Getter @Setter @ToString(callSuper = true)
@Entity
public class DefaultUser extends AbstractUser {
    @OneToMany(mappedBy = "id")
    private List<DefaultInvite> invites;
}
