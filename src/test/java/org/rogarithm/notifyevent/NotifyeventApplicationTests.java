package org.rogarithm.notifyevent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;

@SpringBootTest
@DisabledIf(expression = "#{systemProperties['SKIP_SPRING_BOOT_TESTS'] == 'true'}", reason = "Skipping Spring Boot tests")
class NotifyeventApplicationTests {

	@Test
	void contextLoads() {
	}

}
