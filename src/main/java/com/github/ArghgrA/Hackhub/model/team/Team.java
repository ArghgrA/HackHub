package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import com.github.ArghgrA.Hackhub.model.pagamento.PagamentoKind;
import com.github.ArghgrA.Hackhub.model.utente.MembroTeam;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
public class Team {
    @Id
    private UUID id;
    private String nome;
    //messo cosi pk il tipo Byte
    private String logourl;
    //private HashMap<PagamentoKind, Pagamento> metodiPagamento;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pagamento> metodiPagamento;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembroTeam> membri;

    public Team(String nome, String logourl) {
        id = UUID.randomUUID();
        this.nome = nome;
        this.logourl = logourl;
        metodiPagamento = new ArrayList<Pagamento>();
        membri = new ArrayList<>();
    }

    /*
    public void setMetodoPagamento(PagamentoKind pagamentoKind, Pagamento pagamento) {
        metodiPagamento.put(pagamentoKind,pagamento);
    }

     */
}
