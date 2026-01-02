package br.com.rodneybarreto.maiagent.tool;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class AssistantTools {

    private static final Map<String, Double> DAILY_BASE_PRICE = Map.of(
            "economico", 150.0,
            "suv", 280.0,
            "premium", 420.0
    );

    private static final Map<String, Double> INSURANCE_RATE = Map.of(
            "economico", 0.05,
            "suv", 0.08,
            "premium", 0.12
    );

    @Tool("Calcula o valor total do aluguel corporativo com base na categoria do carro e número de dias.")
    public String calculateQuotation(String category, int days) {
        double base = DAILY_BASE_PRICE.get(category.toLowerCase(Locale.ROOT));
        double rate = INSURANCE_RATE.get(category.toLowerCase(Locale.ROOT));

        double total = (base * days) * (1 + rate);
        log.info("total da simulação: {}", total);

        return String.format(
                "Cotação: %s por %d dias -> R$ %.2f (inclui seguro %.0f%%)",
                category, days, total, (rate * 100)
        );
    }

}
