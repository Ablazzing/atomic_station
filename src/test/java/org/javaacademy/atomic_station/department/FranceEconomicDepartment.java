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
@ActiveProfiles("france")
public class FranceEconomicDepartment {
    @Autowired
    private EconomicDepartment economicDepartment;

    @Test
    public void calculateBeforeLimit() {
        BigDecimal result = economicDepartment.calculateIncome(1_000_000);
        BigDecimal expected = valueOf(500_000);
        assertEquals(expected, result.setScale(0));
    }

    @Test
    public void calculateAfterLimit() {
        BigDecimal result = economicDepartment.calculateIncome(3_000_000_000L);
        BigDecimal expected = valueOf(500_000_000L + 495_000_000L + 490_050_000L);
        assertEquals(expected, result.setScale(0));
    }
}
