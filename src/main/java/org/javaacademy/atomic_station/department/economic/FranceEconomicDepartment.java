package org.javaacademy.atomic_station.department.economic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

@Service
@Profile("france")
public class FranceEconomicDepartment extends EconomicDepartment {
    @Value("${country.economic.change-rate-count}")
    private long changeRateCountEnergy;
    @Value("${country.economic.coefficient-for-per-change-rate}")
    private BigDecimal coefficientForPerChangeRate;

    @Override
    public BigDecimal calculateIncome(long countEnergyKwtHour) {
        if (countEnergyKwtHour - limitBaseLimit <= 0) {
            return baseRate.multiply(valueOf(countEnergyKwtHour));
        }
        BigDecimal incomeBaseRate = baseRate.multiply(valueOf(limitBaseLimit));
        return incomeBaseRate.add(calculateIncomeAfterBaseRate(countEnergyKwtHour));
    }

    private BigDecimal calculateIncomeAfterBaseRate(long countEnergyKwtHour) {
        long countEnergyAfterLimit = countEnergyKwtHour - limitBaseLimit;
        BigDecimal totalResult = ZERO;
        BigDecimal rate = baseRate;
        while (countEnergyAfterLimit - changeRateCountEnergy > 0) {
            rate = rate.multiply(coefficientForPerChangeRate);
            totalResult = totalResult.add(rate.multiply(valueOf(changeRateCountEnergy)));
            countEnergyAfterLimit -= changeRateCountEnergy;
        }
        if (countEnergyAfterLimit > 0) {
            rate = rate.multiply(coefficientForPerChangeRate);
            totalResult = totalResult.add(rate.multiply(valueOf(countEnergyAfterLimit)));
        }

        return totalResult;
    }
}
