package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.abstraction.Team;
import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import com.github.ArghgrA.Hackhub.model.other.payment.PaymentKind;
import com.github.ArghgrA.Hackhub.model.other.payment.address.AbstractPaymentAddress;
import com.github.ArghgrA.Hackhub.model.other.payment.address.PaymentAddress;
import com.github.ArghgrA.Hackhub.model.user.TeamMember;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTeam implements Team<UUID> {
    @Setter(AccessLevel.NONE)
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TeamMember> members = new LinkedList<>();

    private String name;

    @ManyToMany(mappedBy = "teams")
    private List<AbstractHackathon> hackathons = new LinkedList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AbstractPaymentAddress> addresses = new LinkedList<>();

    public void addMember(TeamMember member) {
        if (member == null) return;
        if (!members.contains(member)) {
            members.add(member);
            member.setTeam(this);
        };
    }

    public void addHackathon(AbstractHackathon hackathon){
        if(hackathon == null) return;
        if (!hackathons.contains(hackathon)){
            hackathons.add(hackathon);
            hackathon.addTeam(this);
        }
    }

    public void addPaymentAddress(AbstractPaymentAddress address) {
        if(address == null) return;
        if (!addresses.contains(address)) {
            // delete old payment method of same kind if exist
            findAddressByType(address.getClass()).ifPresent(addresses::remove);
            addresses.add(address);
            address.setTeam(this);
        };
    }

    private  <T extends AbstractPaymentAddress> Optional<T> findAddressByType(Class<T> type) {
        return addresses.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .findFirst();
    }

    public Optional<AbstractPaymentAddress> findAddressByKind(PaymentKind kind){
            return addresses.stream()
                    .filter(kind::matches)
                    .findFirst();
    }

    public void removeMember(TeamMember member) {
        if (member == null) return;
        if (members.contains(member)) {
            members.remove(member);
            member.setTeam(null);
        }
    }


}
