package rocha.andre.grafos.service;

import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.CentroDados;

import java.util.ArrayList;
import java.util.List;

public class PrimeiroExercicioService {

    public void primeiraQuestao() {
        RedeService redeService = new RedeService();

        // Exemplo 1
        var centroDados = criarCentroDados(10);

        // Exemplo 1


    }

    private static List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }

    private static void exibirGrafo(List<Conexao> conexaos) {
        for (Conexao conexao : conexaos) {
            System.out.println(conexao.getOrigem().getId() + " --(" + conexao.getCusto() + ")--> " + conexao.getDestino().getId());
        }
    }
}