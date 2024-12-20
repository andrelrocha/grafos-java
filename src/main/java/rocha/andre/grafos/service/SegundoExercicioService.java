package rocha.andre.grafos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocha.andre.grafos.algo.BuscaEmLargura;
import rocha.andre.grafos.algo.KruskalAux;
import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Cor;
import rocha.andre.grafos.models.Rede;

import java.util.ArrayList;
import java.util.List;

public class SegundoExercicioService {

    public void segundaQuestao() {
        var rede = new Rede();
        var numCD = 10;
        var centroDados = criarCentroDados(numCD);
        rede.setCentroDados(centroDados);

        var redeService = new RedeService();

        redeService.adicionarConexoes(new Conexao(centroDados.get(0), centroDados.get(numCD-1), 10, rede.getConexoes()), rede);
        for (int i = 0; i < numCD - 1; i++) {
            var conexao = new Conexao(centroDados.get(i), centroDados.get(i+1), i, rede.getConexoes());
            redeService.adicionarConexoes(conexao, rede);
            if (i+2 < numCD) {
                conexao = new Conexao(centroDados.get(i), centroDados.get(i+2), i, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }
        }

        var buscaEmLargura = new BuscaEmLargura();

        var centroDeDadosOrigem = rede.getCentroDados().get(0);

        buscaEmLargura.buscaEmLargura(centroDeDadosOrigem);

        if (!eConexa(rede)) {
            throw new IllegalArgumentException("A rede não é conexa.");
        }
        System.out.println("todos os vértices são conectados");

        var kruskalAux = new KruskalAux();

        redeService.exibirGrafo(rede.getConexoes());

        var listaDeConexoes = kruskalAux.kruskal(rede.getCentroDados().size(), rede.getConexoes());

        redeService.exibirGrafo(listaDeConexoes);
    }


    public boolean eConexa(Rede rede) {
        return rede.getCentroDados().stream().anyMatch(c -> c.cor.equals(Cor.PRETO));
    }

    private List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }
}
