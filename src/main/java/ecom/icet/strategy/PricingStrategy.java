package ecom.icet.strategy;

import ecom.icet.Model.Entity.UserTier;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(BigDecimal basePrice, boolean isHighDemand);
    UserTier getTier();
}