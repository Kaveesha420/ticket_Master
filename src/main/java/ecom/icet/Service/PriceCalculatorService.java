package ecom.icet.Service;

import ecom.icet.Model.Entity.*;
import ecom.icet.strategy.PricingStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PriceCalculatorService {

    private final Map<UserTier, PricingStrategy> strategyMap;

    public PriceCalculatorService(List<PricingStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(PricingStrategy::getTier, Function.identity()));
    }

    public BigDecimal calculate(User user, Event event) {
        UserTier userTier = user.getTier();
        PricingStrategy strategy = strategyMap.get(userTier);

        if (strategy == null) {
            throw new RuntimeException("No pricing strategy found for tier: " + userTier);
        }

        return strategy.calculatePrice(BigDecimal.valueOf(event.getBasePrice()), event.isHighDemand());
    }
}