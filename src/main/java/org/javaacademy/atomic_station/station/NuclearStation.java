package org.javaacademy.atomic_station.station;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.javaacademy.atomic_station.department.ReactorDepartment;
import org.javaacademy.atomic_station.department.SecurityDepartment;
import org.javaacademy.atomic_station.department.economic.EconomicDepartment;
import org.javaacademy.atomic_station.exception.NuclearFuelIsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.IntStream;

import static java.math.BigDecimal.ROUND_CEILING;

@Service
@RequiredArgsConstructor
@Slf4j
public class NuclearStation {
    private static final int COUNT_DAYS_IN_YEAR = 365;
    private final ReactorDepartment reactorDepartment;
    private final EconomicDepartment economicDepartment;
    @Autowired
    @Lazy
    private SecurityDepartment securityDepartment;
    private long countAllProducedEnergy = 0;
    @Value("${country.name}")
    private String countryName;
    private int accidentCountAllTime;

    private void startYear() {
        log.info("Атомная станция начала работу");
        long countProducedEnergyForYear = IntStream.range(0, COUNT_DAYS_IN_YEAR)
                .mapToLong(e -> startDay()).reduce(0, Long::sum);
        log.info("Атомная станция закончила работу. За год Выработано {} киловатт/часов",
                countProducedEnergyForYear);
        countAllProducedEnergy += countProducedEnergyForYear;
        int countAccidentForYear = securityDepartment.reset();
        log.info("Количество инцидентов за год: {}", countAccidentForYear);
        BigDecimal income = economicDepartment.calculateIncome(countProducedEnergyForYear);
        log.info("Доход за год составил " + income.setScale(2, ROUND_CEILING));
    }

    public void start(int countWorkYears) {
        log.info("Действие происходит в стране {}", countryName);
        IntStream.range(0, countWorkYears).forEach(e -> startYear());
        log.info("Количество инцидентов за всю работу станции: {}", accidentCountAllTime);
    }

    private long startDay() {
        try {
            long countProducedEnergyForDay = reactorDepartment.run();
            reactorDepartment.stop();
            return countProducedEnergyForDay;
        } catch (NuclearFuelIsEmptyException e) {
            log.warn("Внимание! Происходят работы на атомной станции! Электричества нет!");
            securityDepartment.addAccident();
            reactorDepartment.stop();
            return 0;
        } catch (Exception e) {
            securityDepartment.addAccident();
            return 0;
        }
    }

    public void incrementAccident(int count) {
        accidentCountAllTime += count;
    }
}
