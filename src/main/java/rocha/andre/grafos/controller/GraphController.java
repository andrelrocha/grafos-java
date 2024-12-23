package rocha.andre.grafos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.grafos.models.CentroDadosDTO;
import rocha.andre.grafos.models.Conexao;
import rocha.andre.grafos.service.SegundoExercicioService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class GraphController {
    @GetMapping
    public List<CentroDadosDTO> getGraph() {
        var segundoExercicio = new SegundoExercicioService();
        System.out.println(segundoExercicio);
        return segundoExercicio.gerarGrafoJson();
    }
}
