package rocha.andre.grafos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.models.Rede;
import rocha.andre.grafos.service.PrimeiroExercicioService;
import rocha.andre.grafos.service.RedeService;
import rocha.andre.grafos.service.SegundoExercicioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class GraphController {
    @GetMapping("/primeiraquestao/{query}")
    public List<CentroDadosDTO> primeiraQuestao(@PathVariable String query) {
        var primeiroExercicioService = new PrimeiroExercicioService();
        var redeService = new RedeService();
        var rede = primeiroExercicioService.executar(query);
        return redeService.gerarGrafoJson(rede.getCentroDados());
    }


    @GetMapping("/segundaquestao")
    public List<CentroDadosDTO> getSegundoExercicio() {
        var segundoExercicio = new SegundoExercicioService();
        return segundoExercicio.executar();
    }
}
