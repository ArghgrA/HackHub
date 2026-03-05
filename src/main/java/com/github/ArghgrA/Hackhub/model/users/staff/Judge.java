package com.github.ArghgrA.Hackhub.model.users.staff;

import com.github.ArghgrA.Hackhub.model.hackathon.AbstractHackathon;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @Getter @Setter @ToString(callSuper = true)
@Entity
public class Judge extends AbstractStaff {
}
