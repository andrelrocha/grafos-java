package rocha.andre.grafos.service;

import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Rede;

import java.util.ArrayList;
import java.util.List;

public class PrimeiroExercicioService {

    public void primeiraQuestao() {
        Rede rede = new Rede();
        RedeService redeService = new RedeService();

        var numCD = 10;

        // Exemplo 1
        var centroDados = criarCentroDados(numCD);
        rede.setCentroDados(centroDados);
        List<Conexao> conexoes = new ArrayList<>();
        redeService.adicionarConexoes(new Conexao(centroDados.get(0), centroDados.get(numCD-1), 10, rede.getConexoes()), conexoes);
        for (int i = 0; i < numCD - 1; i++) {
            var conexao = new Conexao(centroDados.get(i), centroDados.get(i+1), i, rede.getConexoes());
            redeService.adicionarConexoes(conexao, conexoes);
            if (i+2 < numCD) {
                conexao = new Conexao(centroDados.get(i), centroDados.get(i+2), i, rede.getConexoes());
                redeService.adicionarConexoes(conexao, conexoes);
            }
        }

        exibirGrafo(conexoes);

        // Exemplo 2
        for (int i = 0; i < numCD - 1; i++) {
            if (i + 3 < numCD) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 3), i + 2, rede.getConexoes());
                redeService.adicionarConexoes(conexao, conexoes);
            }

            if (i == numCD - 4) {
                var conexaoExtra = new Conexao(centroDados.get(i), centroDados.get(numCD - 1), i, rede.getConexoes());
                redeService.adicionarConexoes(conexaoExtra, conexoes);
            }
        }


        exibirGrafo(conexoes);

        // Exemplo 3
        for (int i = 0; i < numCD - 1; i++) {
            if (i + 4 < numCD) {
                var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 4), i + 3, rede.getConexoes());
                redeService.adicionarConexoes(conexao, conexoes);
            }

            if (i == numCD - 5) {
                var conexaoExtra = new Conexao(centroDados.get(i), centroDados.get(numCD - 1), i, rede.getConexoes());
                redeService.adicionarConexoes(conexaoExtra, conexoes);
            }
        }

        exibirGrafo(conexoes);
    }

    private static List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }


    private static void exibirGrafo(List<Conexao> conexoes) {
        for (Conexao conexao : conexoes) {
            System.out.println(conexao.getOrigem().getId() + " --- " + conexao.getCusto() + " --- " + conexao.getDestino().getId());
        }
        System.out.println("\n-------------------\n");
    }
}