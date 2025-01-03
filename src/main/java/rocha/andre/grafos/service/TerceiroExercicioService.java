package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;
import rocha.andre.grafos.algo.BuscaEmLargura;
import rocha.andre.grafos.algo.Kruskal;
import rocha.andre.grafos.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TerceiroExercicioService {

    public List<CentroDadosDTO> executar() {
        List<CentroDados> centroDados = criarCentroDados(10);
        var rede = new Rede();
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

        var buscaEmLargura = new BuscaEmLargura();
        var centroDeDadosOrigem = rede.getCentroDados().get(0);

        buscaEmLargura.buscaEmLargura(centroDeDadosOrigem);
        if (!eConexa(rede)) {
            throw new IllegalArgumentException("A rede não é conexa.");
        }

        System.out.println("todos os vértices são conectados");

        var conexao = rede.getConexoes().get(0);
        var conexaoDesconectaRede = verificaDesconexao(rede, conexao);

        if (conexaoDesconectaRede) {
            System.out.println("A remoção da aresta desconecta a rede.");
        } else {
            System.out.println("A remoção da aresta não desconecta a rede.");
        }

        return redeService.gerarGrafoJson(rede.getCentroDados());
    }

    public boolean eConexa(Rede rede) {
        return rede.getCentroDados().stream().allMatch(c -> c.cor.equals(Cor.PRETO));
    }


    public boolean verificaDesconexao(Rede rede, Conexao conexao) {
        if (!eConexa(rede)) return true;

        var origem = conexao.getOrigem();
        var destino = conexao.getDestino();
        origem.getConexoes().remove(conexao);
        destino.getConexoes().remove(conexao);
        rede.getConexoes().remove(conexao);

        for (CentroDados centro : rede.getCentroDados()) {
            centro.cor = Cor.BRANCO;
            centro.distancia = Integer.MAX_VALUE;
            centro.pai = null;
        }

        var buscaEmLargura = new BuscaEmLargura();
        buscaEmLargura.buscaEmLargura(rede.getCentroDados().get(0));

        return !eConexa(rede);
    }


    private List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }
}
