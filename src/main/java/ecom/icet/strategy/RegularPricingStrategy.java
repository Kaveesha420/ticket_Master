package ecom.icet.strategy;

import ecom.icet.Model.Entity.UserTier;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public UserTier getTier() { return UserTier.REGULAR; }

    @Override
    public BigDecimal calculatePrice(BigDecimal basePrice, boolean isHighDemand) {
        return basePrice;
    }
}