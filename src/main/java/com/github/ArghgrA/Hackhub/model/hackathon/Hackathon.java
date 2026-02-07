package com.github.ArghgrA.Hackhub.model.hackathon;
import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.utente.staff.Giudice;
import com.github.ArghgrA.Hackhub.model.utente.staff.Mentore;
import com.github.ArghgrA.Hackhub.model.premio.Premio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
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
    private List<Mentore> mentori;
}
