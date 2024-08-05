package com.lahiru.demo;

import com.lahiru.demo.exception.RentalAgreementException;
import com.lahiru.demo.model.RentalAgreement;
import com.lahiru.demo.service.RentalService;
import com.lahiru.demo.service.RentalServiceImpl;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.HolidayCalendar;
import com.lahiru.demo.util.RentalCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
class ToolRentalApplicationTests {
	@Autowired
	RentalService rentalService;

	@Configuration
	public static class Config {
		@Bean
		public RentalService rentalService(){
			return new RentalServiceImpl(new DaysCalculator(new HolidayCalendar()), new RentalCalculator());
		}
	}


	@Test
	void rental_test_1() {
		String toolCode = "JAKR";
		LocalDate checkoutDate = LocalDate.of(2015,9,3); // 9/3/15
		int rentalDays = 5;
		int discount = 101;
		RentalAgreementException exception = Assertions.assertThrows(RentalAgreementException.class, () ->{
			RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		});
		Assertions.assertTrue(exception.getMessage().contains("Discount percent must be between 0 to 100"));
	}

	@Test
	void rental_test_2() throws RentalAgreementException {
		String toolCode = "LADW";
		LocalDate checkoutDate = LocalDate.of(2020,7,2); // 7/2/20
		int rentalDays = 3;
		int discount = 10;
		RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		agreement.print();
	}

	@Test
	void rental_test_3() throws RentalAgreementException {
		String toolCode = "CHNS";
		LocalDate checkoutDate = LocalDate.of(2015,7,2); // 7/2/15
		int rentalDays = 5;
		int discount = 25;
		RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		agreement.print();
	}

	@Test
	void rental_test_4() throws RentalAgreementException {
		String toolCode = "JAKD";
		LocalDate checkoutDate = LocalDate.of(2015,9,3); // 9/3/15
		int rentalDays = 6;
		int discount = 0;
		RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		agreement.print();
	}

	@Test
	void rental_test_5() throws RentalAgreementException {
		String toolCode = "JAKR";
		LocalDate checkoutDate = LocalDate.of(2015,7,2); // 7/2/15
		int rentalDays = 9;
		int discount = 0;
		RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		agreement.print();
	}

	@Test
	void rental_test_6() throws RentalAgreementException {
		String toolCode = "JAKR";
		LocalDate checkoutDate = LocalDate.of(2020,7,2); // 7/2/20
		int rentalDays = 4;
		int discount = 50;
		RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		agreement.print();
	}

	@Test
	void rental_test_7_rental_days_zero() throws RentalAgreementException {
		String toolCode = "JAKR";
		LocalDate checkoutDate = LocalDate.of(2020,7,2); // 7/2/20
		int rentalDays = 0;
		int discount = 15;
		RentalAgreementException exception = Assertions.assertThrows(RentalAgreementException.class, () ->{
			RentalAgreement agreement = rentalService.checkout(toolCode,rentalDays,checkoutDate,discount);
		});
		Assertions.assertTrue(exception.getMessage().contains("Rental days must be greater than 0"));
	}


}
