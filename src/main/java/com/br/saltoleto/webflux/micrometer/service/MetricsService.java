import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetricsService {

    private final MeterRegistry meterRegistry;

    @Autowired
    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
        public Timer.Sample startTimer() {
        return Timer.start(meterRegistry);
    }

    public void recordTimerMetric(String metricName, long durationInMillis, Map<String, String> tags) {
        List<Tag> micrometerTags = tags.entrySet().stream()
                .map(entry -> Tag.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

        Timer timer = Timer.builder(metricName)
                .description("Tempo gasto em alguma operação")
                .tags(micrometerTags)
                .register(meterRegistry);

        timer.record(durationInMillis);
    }
}
