package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;
import rocha.andre.grafos.algo.Kruskal;
import rocha.andre.grafos.algo.SubGrafoEconomicoConfiavel;
import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Rede;

import java.util.*;

@Service
public class QuartoExercicioService {

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

            if (i + 3 < centroDados.size()) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 3), i + 2, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }

            if (i == centroDados.size() - 4) {
                for (int j = i + 1; j < centroDados.size(); j++) {
                    var destino = (j + 3) % centroDados.size();
                    var conexaoVolta = new Conexao(centroDados.get(j), centroDados.get(destino), j + 2, rede.getConexoes());
                    redeService.adicionarConexoes(conexaoVolta, rede);
                }
            }
        }

        var kruskal = new Kruskal();

        var arvoreGeradoraMinimaConexoes = kruskal.kruskal(rede.getCentroDados().size(), rede.getConexoes());

        var subGrafoEconomicoConfiavel = new SubGrafoEconomicoConfiavel();

        var subgrafoFinal = subGrafoEconomicoConfiavel.gerarSubgrafoConfiavel(rede.getCentroDados().size(), arvoreGeradoraMinimaConexoes, rede.getConexoes());

        var listaCentroDados = redeService.gerarCentroDados(subgrafoFinal);
        //var listaCentroDados = redeService.gerarCentroDados(rede.getConexoes());

        return redeService.gerarGrafoJson(listaCentroDados);
    }

    private List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }




}
