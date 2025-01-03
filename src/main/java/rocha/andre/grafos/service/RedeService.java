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

    private CentroDados findCentroDadosById(List<CentroDados> centroDadosList, int id) {
        for (CentroDados centro : centroDadosList) {
            if (centro.getId() == id) {
                return centro;
            }
        }
        return null;
    }


    public List<CentroDadosDTO> gerarGrafoJson(List<CentroDados> centroDados) {
        List<CentroDadosDTO> grafo = new ArrayList<>();

        for (CentroDados cd : centroDados) {
            List<CentroDadosDTO.Adjacencia> adjacencias = new ArrayList<>();

            for (Conexao conexao : cd.getConexoes()) {
                if (conexao.getDestino().getId() != cd.getId()) {
                    CentroDadosDTO.Adjacencia adjacencia = new CentroDadosDTO.Adjacencia(
                            conexao.getDestino().getId(), conexao.getCusto());

                    adjacencias.add(adjacencia);
                }
            }

            var vertice = new CentroDadosDTO(cd.getId(), adjacencias);
            grafo.add(vertice);
        }

        return grafo;
    }
}
