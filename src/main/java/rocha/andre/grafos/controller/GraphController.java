package rocha.andre.grafos.controller;

import org.springframework.web.bind.annotation.*;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.service.*;

import java.util.List;

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

    @GetMapping("/terceiraquestao")
    public List<CentroDadosDTO> terceiraQuestao() {
        var terceiroExercicio = new TerceiroExercicioService();
        return terceiroExercicio.executar();
    }

    @GetMapping("/quartaquestao")
    public List<CentroDadosDTO> quartaQuestao() {
        var quartoExercicio = new QuartoExercicioService();
        return quartoExercicio.executar();
    }
}
