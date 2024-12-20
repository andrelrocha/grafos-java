package rocha.andre.grafos.algo;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Conexao;

import java.util.*;

public class KruskalAux {

    public List<Conexao> kruskal(int numCentros, List<Conexao> conexoes) {
        List<Conexao> mst = new ArrayList<>();
        int[] conjunto = new int[numCentros]; // Representa o conjunto de cada vértice

        // Inicializa cada vértice para ser seu próprio conjunto
        for (int i = 0; i < numCentros; i++) {
            conjunto[i] = i;
        }

        // Ordena as conexões por peso
        Collections.sort(conexoes);

        // Processa cada conexão em ordem crescente
        for (Conexao conexao : conexoes) {
            int origem = conexao.getOrigem().getId();
            int destino = conexao.getDestino().getId();

            // Verifica se origem e destino estão em conjuntos diferentes
            if (conjunto[origem] != conjunto[destino]) {
                mst.add(conexao); // Adiciona a conexão à MST

                // Atualiza os conjuntos para unir origem e destino
                int conjuntoAntigo = conjunto[destino];
                int conjuntoNovo = conjunto[origem];
                for (int i = 0; i < numCentros; i++) {
                    if (conjunto[i] == conjuntoAntigo) {
                        conjunto[i] = conjuntoNovo;
                    }
                }
            }
        }

        return mst;
    }

    /*
    public List<Conexao> construirRedeRobusta(int numVertices, List<Conexao> conexoes) {
        // Primeiro, gera a árvore geradora mínima (MST)
        var mst = kruskal(numVertices, conexoes);

        // Cria uma lista de graus dos vértices
        int[] grau = new int[numVertices];

        // Preenche os graus dos vértices com as conexões da MST
        for (Conexao conexao : mst) {
            grau[conexao.getOrigem().getId()]++;
            grau[conexao.getDestino().getId()]++;
        }

        // Agora, adiciona conexões extras para garantir que o grafo seja 2-conectado
        // Adiciona conexões entre vértices que ainda possuem grau 1
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (grau[i] == 1 && grau[j] == 1) {
                    // Se ambos os vértices têm grau 1, conecta-os
                    mst.add(new Conexao(new CentroDados(i), new CentroDados(j), 1)); // Custo arbitrário
                    grau[i]++;
                    grau[j]++;
                    break; // Já podemos parar de tentar conectar i com j
                }
            }
        }

        return mst;
    }
     */

}
