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
    @OneToMany(mappedBy = "receiver")
    private List<DefaultInvite> invites;

    public void addInvite(DefaultInvite invite) {
        if(invite==null) return;
        if(!invites.contains(invite)) {
            invites.add(invite);
            invite.setReceiver(this);
        }
    }
}
