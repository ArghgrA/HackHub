package com.github.ArghgrA.Hackhub.model.team;

import com.github.ArghgrA.Hackhub.model.pagamento.Pagamento;
import com.github.ArghgrA.Hackhub.model.pagamento.PagamentoKind;
import com.github.ArghgrA.Hackhub.model.utente.MembroTeam;
import lombok.Getter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class Team {
    private UUID id;
    private String nome;
    private Byte[] logo;
    private HashMap<PagamentoKind, Pagamento> metodiPagamento;
    private List<MembroTeam> membri;

    public Team(String nome, Byte[] logo) {
        id = UUID.randomUUID();
        this.nome = nome;
        this.logo = logo;
        metodiPagamento = new HashMap<>();
        membri = new ArrayList<>();
    }

    public void setMetodoPagamento(PagamentoKind pagamentoKind, Pagamento pagamento) {
        metodiPagamento.put(pagamentoKind,pagamento);
    }
}
