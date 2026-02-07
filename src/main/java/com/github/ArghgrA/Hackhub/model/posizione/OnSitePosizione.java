package com.github.ArghgrA.Hackhub.model.posizione;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class OnSitePosizione implements Posizione{
    private final double latitudine;
    private final double longitudine;
}
