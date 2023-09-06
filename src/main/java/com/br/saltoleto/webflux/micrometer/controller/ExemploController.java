import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/exemplo")
public class ExemploController {

    private final MetricsService metricsService;

    @Autowired
    public ExemploController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/alguma-operacao")
    public String realizarAlgumaOperacao(@RequestParam long tempoGasto) {
        // Sua lógica aqui

        // Crie um mapa de tags (pode personalizar conforme necessário)
        Map<String, String> tags = new HashMap<>();
        tags.put("tipo", "operacao");
        tags.put("cliente", "12345");

        // Registre a métrica de timer com as tags usando o serviço de métricas
        metricsService.recordTimerMetric("minha_metrica_timer", tempoGasto, tags);

        return "Operação concluída!";
    }
}
