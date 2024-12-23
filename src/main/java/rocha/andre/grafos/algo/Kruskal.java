package rocha.andre.grafos.algo;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.CentroDadosDTO;
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

    public List<CentroDados> gerarCentroDados(List<Conexao> conexoes) {
        List<CentroDados> centroDadosList = new ArrayList<>();

        // Criação dos centros de dados e conexões
        for (Conexao conexao : conexoes) {
            var origemId = conexao.getOrigem().getId();
            var destinoId = conexao.getDestino().getId();

            // Verifica se o centro de dados de origem já foi adicionado, caso contrário, cria e adiciona
            CentroDados origem = findCentroDadosById(centroDadosList, origemId);
            if (origem == null) {
                origem = new CentroDados(origemId);
                centroDadosList.add(origem);
            }

            // Verifica se o centro de dados de destino já foi adicionado, caso contrário, cria e adiciona
            CentroDados destino = findCentroDadosById(centroDadosList, destinoId);
            if (destino == null) {
                destino = new CentroDados(destinoId);
                centroDadosList.add(destino);
            }

            // Adicionar a conexão nos centros de dados de origem e destino
            origem.adicionarConexao(conexao);
            destino.adicionarConexao(conexao);
        }

        return centroDadosList;
    }

    // Método auxiliar para encontrar um CentroDados pelo seu ID na lista
    private CentroDados findCentroDadosById(List<CentroDados> centroDadosList, int id) {
        for (CentroDados centro : centroDadosList) {
            if (centro.getId() == id) {
                return centro;
            }
        }
        return null;
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
