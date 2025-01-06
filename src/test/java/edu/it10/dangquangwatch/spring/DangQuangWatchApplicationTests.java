package edu.it10.dangquangwatch.spring;

import edu.it10.dangquangwatch.spring.helper.ParameterNameMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import edu.it10.dangquangwatch.spring.helper.PasswordHelper;

@SpringBootTest
class DangQuangWatchApplicationTests {

	@Test
	void isValidPassword() {
		Assertions.assertTrue(PasswordHelper.isValidPassword("Password123456789@"));
	}

	@Test
	void isCorrectParameterName() {
		Assertions.assertEquals("Tên kính mắt", ParameterNameMapper.getFriendlyName("tenkinhmat"));
	}
}
