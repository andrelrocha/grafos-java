package rocha.andre.grafos.service;

import org.springframework.stereotype.Service;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.Rede;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedeService {
    public void adicionarCentroDados(CentroDados centroDados, List<CentroDados> centrosDados) {
        if (!centrosDados.contains(centroDados)) {
            centrosDados.add(centroDados);
        }
    }

    public void exibirGrafo(List<Conexao> conexoes) {
        for (Conexao conexao : conexoes) {
            System.out.println(conexao.getOrigem().getId() + " --- " + conexao.getCusto() + " --- " + conexao.getDestino().getId());
        }
        System.out.println("\n-------------------\n");
    }

    public void adicionarConexoes(Conexao conexao, Rede rede) {
        conexao.getOrigem().adicionarConexao(conexao);
        conexao.getDestino().adicionarConexao(conexao);
        rede.addConexao(conexao);
    }

    public void exibirRede(Rede rede) {
        System.out.println("Centro de Dados:");
        for (CentroDados centroDados : rede.getCentroDados()) {
            System.out.println("ID: " + centroDados.getId());
        }

        System.out.println("Conexões:");
        for (Conexao conexao : rede.getConexoes()) {
            System.out.println("Origem: " + conexao.getOrigem().getId() +
                    ", Destino: " + conexao.getDestino().getId() +
                    ", Custo: " + conexao.getCusto());
        }
    }


    public List<CentroDadosDTO> gerarGrafoJson(List<CentroDados> centroDados) {
        List<CentroDadosDTO> grafo = new ArrayList<>();

        for (CentroDados cd : centroDados) {
            // Criando as adjacências para o centro de dados
            List<CentroDadosDTO.Adjacencia> adjacencias = new ArrayList<>();

            for (Conexao conexao : cd.getConexoes()) {
                // Verificando se o destino é diferente do centro de dados atual para evitar auto-conexões
                if (conexao.getDestino().getId() != cd.getId()) {
                    CentroDadosDTO.Adjacencia adjacencia = new CentroDadosDTO.Adjacencia(
                            conexao.getDestino().getId(), conexao.getCusto());

                    // Verifica se a adjacência já foi adicionada (evitando duplicatas)
                    boolean alreadyExists = adjacencias.stream()
                            .anyMatch(adj -> adj.destino() == adjacencia.destino());

                    if (!alreadyExists) {
                        adjacencias.add(adjacencia);
                    }
                }
            }

            // Criando o vértice para o centro de dados
            CentroDadosDTO vertice = new CentroDadosDTO(cd.getId(), adjacencias);
            grafo.add(vertice);
        }

        return grafo;
    }
}
