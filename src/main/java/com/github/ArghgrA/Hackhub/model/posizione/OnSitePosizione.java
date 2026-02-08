package com.github.ArghgrA.Hackhub.model.posizione;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor @Getter
@Entity
@NoArgsConstructor
public class OnSitePosizione extends Posizione{

    private double latitudine;

    private double longitudine;
}
