package com.github.ArghgrA.Hackhub.model.posizione;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
public class OnlinePosizione extends Posizione{
    private String URL;
}
