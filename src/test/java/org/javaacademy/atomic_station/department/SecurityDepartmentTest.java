package org.javaacademy.atomic_station.department;

import org.javaacademy.atomic_station.department.economic.EconomicDepartment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class SecurityDepartmentTest {
    @MockBean
    private EconomicDepartment economicDepartment;
    @Autowired
    private SecurityDepartment securityDepartment;

    @Test
    void addAccident() {
        securityDepartment.addAccident();
        int countAccidents = securityDepartment.getCountAccidents();
        assertEquals(1, countAccidents);
    }

    @Test
    void resetAccident() {
        assertEquals(0, securityDepartment.getCountAccidents());
        securityDepartment.addAccident();
        securityDepartment.reset();
        assertEquals(0, securityDepartment.getCountAccidents());
    }
}
