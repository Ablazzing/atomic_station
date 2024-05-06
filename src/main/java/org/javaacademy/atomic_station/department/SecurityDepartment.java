package org.javaacademy.atomic_station.department;

import lombok.RequiredArgsConstructor;
import org.javaacademy.atomic_station.station.NuclearStation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityDepartment {
    private final NuclearStation nuclearStation;
    private int accidentCountPeriod;

    public void addAccident() {
        accidentCountPeriod++;
    }

    public int getCountAccidents() {
        return accidentCountPeriod;
    }

    public int reset() {
        int result = accidentCountPeriod;
        nuclearStation.incrementAccident(accidentCountPeriod);
        accidentCountPeriod = 0;
        return result;
    }
}
