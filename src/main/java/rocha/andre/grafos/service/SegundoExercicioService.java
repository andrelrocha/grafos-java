package rocha.andre.grafos.service;

import rocha.andre.grafos.algo.BuscaEmLargura;
import rocha.andre.grafos.algo.KruskalAux;
import rocha.andre.grafos.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegundoExercicioService {

    public List<CentroDadosDTO> executar() {
        List<CentroDados> centroDados = criarCentroDados(10);
        Rede rede = new Rede();
        rede.setCentroDados(centroDados);

        var redeService = new RedeService();

        for (int i = 0; i < centroDados.size() - 1; i++) {
            if (centroDados.get(i).getId() != centroDados.get(i + 1).getId()) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 1), i, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }

            if (i + 2 < centroDados.size()) {
                if (centroDados.get(i).getId() != centroDados.get(i + 2).getId()) {
                    var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 2), i, rede.getConexoes());
                    redeService.adicionarConexoes(conexao, rede);
                }
            }
        }

        //iniciando questao
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



        return redeService.gerarGrafoJson(centroDados);
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
