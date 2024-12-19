package rocha.andre.grafos.algo;

import rocha.andre.grafos.models.Conexao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAux {
    private int[] pai;
    private int[] peso;

    public KruskalAux(int tam) {
        pai = new int[tam];
        peso = new int[tam];

        // Inicializa cada vértice para ser o líder de seu próprio conjunto
        for (int i = 0; i < tam; i++) {
            pai[i] = i;
            peso[i] = 0;
        }
    }

    // Método para encontrar o líder do conjunto, com compressão de caminho
    public int busca(int x) {
        if (pai[x] != x) {
            pai[x] = busca(pai[x]);  // busca em recurssão
        }
        return pai[x];
    }

    // Método para unir dois conjuntos, com união por rank
    public void union(int x, int y) {
        int rootX = busca(x);
        int rootY = busca(y);

        if (rootX != rootY) {
            // União por rank
            if (peso[rootX] > peso[rootY]) {
                pai[rootY] = rootX;
            } else if (peso[rootX] < peso[rootY]) {
                pai[rootX] = rootY;
            } else {
                pai[rootY] = rootX;
                peso[rootX]++;  // Aumenta o rank se ambos os conjuntos tiverem o mesmo tamanho
            }
        }
    }


    List<Conexao> kruskal(int numVertices, List<Conexao> conexoes) {
        List<Conexao> mst = new ArrayList<>();
        KruskalAux kruskalAux = new KruskalAux(numVertices);

        // Ordena as arestas por peso
        Collections.sort(conexoes);

        // Processa as arestas em ordem crescente
        for (Conexao conexao : conexoes) {
            int origem = conexao.getOrigem().getId();
            int destino = conexao.getDestino().getId();

            // Verifica se os vértices estão em conjuntos diferentes
            if (kruskalAux.busca(origem) != kruskalAux.busca(destino)) {
                mst.add(conexao);  // Adiciona a aresta à MST

                // União dos conjuntos
                kruskalAux.union(origem, destino);
            }
        }

        return mst;
    }
}
