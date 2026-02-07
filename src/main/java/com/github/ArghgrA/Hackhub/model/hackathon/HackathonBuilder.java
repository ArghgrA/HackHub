package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.premio.Premio;
import com.github.ArghgrA.Hackhub.model.utente.staff.Giudice;
import com.github.ArghgrA.Hackhub.model.utente.staff.Mentore;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
public class HackathonBuilder {
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String nome;
    private String regolamento;
    private Posizione posizione;
    private Premio premio;
    private Intervallo intervallo;
    private int maxNumMebri;
    @Setter(AccessLevel.NONE)
    private Giudice giudice;
    @Setter(AccessLevel.NONE)
    private List<Mentore> mentori;

    public void setMaxNumMebri(int maxNumMebri){
        if(maxNumMebri <= 1) throw new IllegalArgumentException("");
        this.maxNumMebri = maxNumMebri;
    }
    public Hackathon getResult(){
        return new Hackathon(
                UUID.randomUUID(),
                nome,
                regolamento,
                posizione,
                premio,
                intervallo,
                maxNumMebri,
                null,
                new ArrayList<Mentore>()
        );
    }
}
