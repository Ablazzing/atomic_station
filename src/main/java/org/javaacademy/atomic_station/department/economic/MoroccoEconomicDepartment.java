package org.javaacademy.atomic_station.department.economic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Service
@Profile("morocco")
public class MoroccoEconomicDepartment extends EconomicDepartment {
    @Value("${country.economic.over-limit-rate}")
    protected BigDecimal overLimitRate;

    @Override
    public BigDecimal calculateIncome(long countEnergyKwtHour) {
        if (countEnergyKwtHour - limitBaseLimit <= 0) {
            return baseRate.multiply(valueOf(countEnergyKwtHour));
        }
        BigDecimal incomeBaseRate = baseRate.multiply(valueOf(limitBaseLimit));
        return incomeBaseRate.add(calculateIncomeAfterLimit(countEnergyKwtHour));
    }

    private BigDecimal calculateIncomeAfterLimit(long countEnergyKwtHour) {
        long energyAfterLimit = countEnergyKwtHour - limitBaseLimit;
        return energyAfterLimit > 0
                ? overLimitRate.multiply(BigDecimal.valueOf(energyAfterLimit))
                : ZERO;
    }
}
