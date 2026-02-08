package com.github.ArghgrA.Hackhub.model.posizione;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class OnlinePosizione extends Posizione{
    private String URL;
}
