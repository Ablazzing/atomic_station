package org.javaacademy.atomic_station.department;

import org.javaacademy.atomic_station.exception.NuclearFuelIsEmptyException;
import org.javaacademy.atomic_station.exception.ReactorAlreadyStartException;
import org.javaacademy.atomic_station.exception.ReactorAlreadyStopException;
import org.springframework.stereotype.Service;

@Service
public class ReactorDepartment {
    private static final long COUNT_ENERGY_KWT_HOUR = 10_000_000L;
    private static final long NUMBER_RUN_WITH_EXCEPTION = 100;
    private boolean isWork;
    private int countStart = 0;

    public long run() throws NuclearFuelIsEmptyException {
        checkIsWorkBeforeStart();
        isWork = true;
        countStart++;
        checkCountStart();
        return COUNT_ENERGY_KWT_HOUR;
    }

    public void stop() {
        checkIsNotWork();
        isWork = false;
    }

    private void checkIsNotWork() {
        if (!isWork) {
            throw new ReactorAlreadyStopException("Реактор уже не работает");
        }
    }
    private void checkIsWorkBeforeStart() {
        if (isWork) {
            throw new ReactorAlreadyStartException("Реактор уже работает");
        }
    }

    private void checkCountStart() throws NuclearFuelIsEmptyException {
        if (countStart == NUMBER_RUN_WITH_EXCEPTION) {
            countStart = 0;
            throw new NuclearFuelIsEmptyException("Нет топлива");
        }
    }
}
