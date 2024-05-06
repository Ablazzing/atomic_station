package org.javaacademy.atomic_station.department.economic;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public abstract class EconomicDepartment {
    @Value("${country.economic.base-rate}")
    protected BigDecimal baseRate;
    @Value("${country.economic.currency}")
    protected String currency;
    @Value("${country.economic.limit-base-limit}")
    protected long limitBaseLimit;

    public abstract BigDecimal calculateIncome(long countEnergyKwtHour);
}
