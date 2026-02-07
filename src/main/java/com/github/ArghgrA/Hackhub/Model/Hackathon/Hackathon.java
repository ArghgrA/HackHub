package com.github.ArghgrA.Hackhub.Model.Hackathon;
import com.github.ArghgrA.Hackhub.Model.Intervallo;
import com.github.ArghgrA.Hackhub.Model.Posizione.Posizione;
import com.github.ArghgrA.Hackhub.Model.Utente.Staff.Giudice;
import com.github.ArghgrA.Hackhub.Model.Utente.Staff.Mentore;
import com.github.ArghgrA.Hackhub.Model.Premio.Premio;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
public class Hackathon {
    private UUID id;
    private String nome;
    private String regolamento;
    private Posizione posizione;

    private Premio premio;
    private Intervallo intervallo;
    private int maxNumMebri;
    @Setter
    private Giudice giudice;
    @Setter
    private Mentore[] mentori;

}
