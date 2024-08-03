package com.lahiru.demo;

import com.lahiru.demo.util.RentalCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
public class RentalCalculatorTest {

    @Autowired
    RentalCalculator rentalCalculator;

    @Test
    public void test_1(){

    }
}
