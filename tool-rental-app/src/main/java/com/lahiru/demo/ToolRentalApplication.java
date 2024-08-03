package com.lahiru.demo;

import com.lahiru.demo.exception.InvalidDayCountException;
import com.lahiru.demo.exception.InvalidInputException;
import com.lahiru.demo.model.Tool;
import com.lahiru.demo.service.RentalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.StringJoiner;

@SpringBootApplication
@Slf4j
public class ToolRentalApplication implements CommandLineRunner {
	@Autowired
	RentalService rentalService;

	public static void main(String[] args) {
		SpringApplication.run(ToolRentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.debug("Application Start");

		Scanner enter = new Scanner(System.in);
		String choice;
		String toolCode;
		int rentalDays;
		int discountPercentage;
		String checkoutDate;
		do {
			System.out.println("Select an option:");
			System.out.println("Enter 1 to Rent a Tool");
			System.out.println("Enter 2 to Exit");
			choice = enter.next();

			if(choice.equalsIgnoreCase ("2")) { break; }

			if("1".equals(choice)){
				System.out.println("Enter Tool Code:");
				StringJoiner joiner = new StringJoiner(",","[","]");
				for(Tool tool: Tool.values()){
					joiner.add(tool.getToolCode());
				}
				System.out.print(joiner.toString()+"> ");

				toolCode = enter.next();
				System.out.println("Enter Checkout Date:");
				checkoutDate = enter.next();
				System.out.println("Enter Rental Days:");
				rentalDays = Integer.parseInt(enter.next());
				System.out.println("Enter Discount %:");
				discountPercentage = Integer.parseInt(enter.next());

			}
		} while(true);

		log.debug("Application End");
	}
}
