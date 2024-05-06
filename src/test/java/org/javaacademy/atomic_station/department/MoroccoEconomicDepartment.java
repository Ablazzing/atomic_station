package org.javaacademy.atomic_station.department;

import org.javaacademy.atomic_station.department.economic.EconomicDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("morocco")
public class MoroccoEconomicDepartment {
    @Autowired
    private EconomicDepartment economicDepartment;

    @Test
    public void calculateBeforeLimit() {
        BigDecimal result = economicDepartment.calculateIncome(1_000_000);
        BigDecimal expected = valueOf(5_000_000);
        assertEquals(expected, result.setScale(0));
    }

    @Test
    public void calculateAfterLimit() {
        BigDecimal result = economicDepartment.calculateIncome(7_000_000_000L);
        BigDecimal expected = valueOf(37_000_000_000L);
        assertEquals(expected, result.setScale(0));
    }
}
