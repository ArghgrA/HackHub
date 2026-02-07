package com.github.ArghgrA.Hackhub.model.hackathon;

import com.github.ArghgrA.Hackhub.model.posizione.Posizione;
import com.github.ArghgrA.Hackhub.model.premio.Premio;


/**
 * Builder per la creazione di istanze di {@link Hackathon}.
 * <p>
 * Questa classe implementa il pattern Builder e consente di costruire
 * un oggetto {@code Hackathon} in modo graduale, rendendo più chiara
 * e sicura l’inizializzazione dei suoi attributi.
 * </p>
 *
 * <p>
 * Alcuni campi non sono modificabili direttamente tramite setter
 * (come {@code id}, {@code giudice} e {@code mentori}) per garantire
 * la coerenza dell’oggetto finale.
 * </p>
 */
public class HackathonBuilder {
    private Hackathon hackathon;

    public HackathonBuilder() {
        hackathon = new Hackathon();
    }

    public void setNome(String nome) {
        hackathon.setNome(nome);
    }

    public void setRegolamento(String regolamento) {
        hackathon.setRegolamento(regolamento);
    }

    public void setPosizione(Posizione posizione) {
        hackathon.setPosizione(posizione);
    }

    public void setPremio(Premio premio) {
        hackathon.setPremio(premio);
    }

    public void setIntervallo(Intervallo intervallo) {
        hackathon.setIntervallo(intervallo);
    }

    public void setMaxNumMembri(int maxNumMembri) {
        if (maxNumMembri <= 1) {
            throw new IllegalArgumentException("");
        }
        hackathon.setMaxNumMebri(maxNumMembri);
    }

    public Hackathon getResult() {
        Hackathon result = hackathon;
        hackathon = new Hackathon();
        return result;
    }
}

