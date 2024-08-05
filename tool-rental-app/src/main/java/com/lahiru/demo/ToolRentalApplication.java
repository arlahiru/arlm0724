package com.lahiru.demo;

import com.lahiru.demo.exception.InvalidInputException;
import com.lahiru.demo.exception.RentalAgreementException;
import com.lahiru.demo.model.RentalAgreement;
import com.lahiru.demo.model.Tool;
import com.lahiru.demo.service.RentalService;
import com.lahiru.demo.util.Formatter;
import com.lahiru.demo.util.InputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringJoiner;

@SpringBootApplication
@Slf4j
@Profile("!test")
public class ToolRentalApplication implements CommandLineRunner {

	@Autowired
	private RentalService rentalService;

	public static void main(String[] args) {
		SpringApplication.run(ToolRentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("Tool Rental Application Started");
		System.out.println();
		System.out.println("<================ Welcome to Tool Rental Application ================>");
		System.out.println();

		Scanner enter = new Scanner(System.in);
		String choice;
		String toolCode;
		String rentalDaysValue;
		String checkoutDateValue;
		String discountPercentageValue;
		do {
			System.out.println("Select an option:");
			System.out.println("1.) Enter 1 to Rent a Tool");
			System.out.println("2.) Enter 2 to Exit");
			choice = enter.next();
			//exit if user enter 2
			if(choice.equalsIgnoreCase ("2")) { break; }
			else if("1".equals(choice)){
				System.out.println("Enter Tool Code:");
				StringJoiner joiner = new StringJoiner(",","[","]");
				for(Tool tool: Tool.values()){
					joiner.add(tool.getToolCode());
				}
				System.out.print(joiner.toString()+"> ");
				toolCode = enter.next();
				System.out.println("Enter Rental Days:");
				rentalDaysValue = enter.next();
				System.out.println("Enter Checkout Date(mm/dd/yy):");
				checkoutDateValue = enter.next();
				System.out.println("Enter Discount %:");
				discountPercentageValue = enter.next();

				try {
					InputValidator.validateInputs(toolCode, rentalDaysValue, checkoutDateValue, discountPercentageValue);
					int rentalDays = Integer.parseInt(rentalDaysValue);
					LocalDate checkoutDate = LocalDate.parse(checkoutDateValue, DateTimeFormatter.ofPattern(Formatter.DATE_FORMAT));
					int discountPercentage =  Integer.parseInt(discountPercentageValue);
					RentalAgreement rentalAgreement = rentalService.checkout(toolCode, rentalDays, checkoutDate, discountPercentage);
					rentalAgreement.print();

				}catch (InvalidInputException | RentalAgreementException e){
					System.out.println(e.getMessage());
					continue;
				}


			}else {
				System.out.println("Invalid Option. Please select valid option.");
			}
		} while(true);
		log.debug("Tool Rental Application Terminated");
	}

}
