package com.github.ArghgrA.Hackhub.model.posizione;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class OnlinePosizione implements Posizione{
    private final String url;
}
