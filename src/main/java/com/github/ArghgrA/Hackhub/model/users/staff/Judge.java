package com.github.ArghgrA.Hackhub.model.users.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
@Entity
public class Judge extends AbstractStaff {
}
