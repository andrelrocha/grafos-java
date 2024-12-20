package rocha.andre.grafos.algo;

import org.springframework.stereotype.Component;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Cor;
import rocha.andre.grafos.models.CentroDados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class BuscaEmLargura {
    public List<CentroDados> buscaEmLargura(CentroDados centroDados) {
        // Inicializa as listas de controle
        List<CentroDados> verticesVisitados = new ArrayList<>();
        Queue<CentroDados> fila = new LinkedList<>();

        // Inicializa o vértice inicial
        centroDados.cor = Cor.CINZA;  // Marca o vértice inicial como CINZA
        centroDados.distancia = 0;  // Distância inicial é 0
        fila.add(centroDados);  // Coloca o vértice inicial na fila
        verticesVisitados.add(centroDados);  // Marca o vértice como visitado

        // Enquanto a fila não estiver vazia, continua o processo de BFS
        while (!fila.isEmpty()) {
            CentroDados v = fila.poll();

            // Visita os vizinhos
            for (Conexao a : v.getConexoes()) {
                CentroDados adj = a.getDestino();
                if (adj.cor == Cor.BRANCO) {  // Só visita vértices BRANCOS
                    adj.cor = Cor.CINZA;  // Marca o vértice como CINZA
                    adj.distancia = v.distancia + 1;  // A distância é a do pai + 1
                    adj.pai = v;  // O pai do vértice adjacente é o vértice atual
                    fila.add(adj);  // Coloca o vértice na fila
                    verticesVisitados.add(adj);  // Marca como visitado
                }
            }

            // Após visitar todos os vizinhos, marca o vértice como PRETO
            v.cor = Cor.PRETO;
        }
        return verticesVisitados;
    }
}
