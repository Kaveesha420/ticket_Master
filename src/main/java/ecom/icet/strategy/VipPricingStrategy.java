package ecom.icet.strategy;

import ecom.icet.Model.Entity.UserTier;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class VipPricingStrategy implements PricingStrategy {
    @Override
    public UserTier getTier() { return UserTier.VIP; }

    @Override
    public BigDecimal calculatePrice(BigDecimal basePrice, boolean isHighDemand) {
        if (isHighDemand) return basePrice;
        return basePrice.multiply(BigDecimal.valueOf(0.90));
    }
}