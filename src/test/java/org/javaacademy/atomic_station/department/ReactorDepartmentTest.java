package org.javaacademy.atomic_station.department;

import lombok.SneakyThrows;
import org.javaacademy.atomic_station.department.economic.EconomicDepartment;
import org.javaacademy.atomic_station.exception.ReactorAlreadyStartException;
import org.javaacademy.atomic_station.exception.ReactorAlreadyStopException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReactorDepartmentTest {
    @MockBean
    private EconomicDepartment economicDepartment;

    @Autowired
    private ReactorDepartment reactorDepartment;

    @Test
    void runSuccess() {
        assertDoesNotThrow(() -> reactorDepartment.run());
    }

    @Test
    @SneakyThrows
    void runTwiceFailed() {
        reactorDepartment.run();
        assertThrows(ReactorAlreadyStartException.class, () -> reactorDepartment.run());
    }

    @Test
    @SneakyThrows
    void stopSuccess() {
        reactorDepartment.run();
        assertDoesNotThrow(() -> reactorDepartment.stop());
    }

    @Test
    @SneakyThrows
    void stopFailed() {
        assertThrows(ReactorAlreadyStopException.class, () -> reactorDepartment.stop());
    }

}
