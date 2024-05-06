package org.javaacademy.atomic_station;

import org.javaacademy.atomic_station.station.NuclearStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("france")
class NuclearStationTest {
	@Autowired
	private NuclearStation nuclearStation;

	@Test
	void runSuccess() {
		Assertions.assertDoesNotThrow(() -> nuclearStation.start(3));
	}
}
