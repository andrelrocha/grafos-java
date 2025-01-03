package rocha.andre.grafos.algo;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Conexao;

import java.util.*;

public class Kruskal {

    public List<Conexao> kruskal(int numCentros, List<Conexao> conexoes) {
        List<Conexao> conexoesArvoreGeradoraMinima = new ArrayList<>();

        var classes = new int[numCentros];

        for (int i = 0; i < numCentros; i++) {
            classes[i] = i;
        }

        Collections.sort(conexoes);

        for (Conexao conexao : conexoes) {
            var origem = conexao.getOrigem().getId();
            var destino = conexao.getDestino().getId();

            if (classes[origem] != classes[destino]) {
                conexoesArvoreGeradoraMinima.add(conexao);

                var classeAntiga = classes[destino];
                var classeNova = classes[origem];
                for (int i = 0; i < numCentros; i++) {
                    if (classes[i] == classeAntiga) {
                        classes[i] = classeNova;
                    }
                }
            }
        }

        return conexoesArvoreGeradoraMinima;
    }
}
