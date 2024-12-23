package rocha.andre.grafos.service;

import rocha.andre.grafos.models.CentroDados;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Rede;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SegundoExercicioService {

    public List<CentroDadosDTO> gerarGrafoJson() {
        List<CentroDados> centroDados = criarCentroDados(10);
        Rede rede = new Rede();
        rede.setCentroDados(centroDados);

        var redeService = new RedeService();

        for (int i = 0; i < centroDados.size() - 1; i++) {
            var conexao = new Conexao(centroDados.get(i), centroDados.get(i + 1), i, rede.getConexoes());
            redeService.adicionarConexoes(conexao, rede);
            if (i + 2 < centroDados.size()) {
                conexao = new Conexao(centroDados.get(i), centroDados.get(i + 2), i, rede.getConexoes());
                redeService.adicionarConexoes(conexao, rede);
            }
        }

        // Montando o grafo no formato usando os records
        List<CentroDadosDTO> grafo = new ArrayList<>();

        for (CentroDados cd : centroDados) {
            // Criando as adjacências para o centro de dados
            List<CentroDadosDTO.Adjacencia> adjacencias = new ArrayList<>();
            for (Conexao conexao : cd.getConexoes()) {
                CentroDadosDTO.Adjacencia adjacencia = new CentroDadosDTO.Adjacencia(
                        conexao.getDestino().getId(), conexao.getCusto());
                adjacencias.add(adjacencia);
            }

            // Criando o vértice para o centro de dados
            CentroDadosDTO vertice = new CentroDadosDTO(cd.getId(), adjacencias);
            grafo.add(vertice);
        }

        return grafo;
    }

    private List<CentroDados> criarCentroDados(int n) {
        List<CentroDados> centroDados = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            centroDados.add(new CentroDados(i));
        }
        return centroDados;
    }
}