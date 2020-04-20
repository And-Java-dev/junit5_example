package com.example.junit5_example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class CalculatorTest {

    private Calculator calculator;
    private boolean moreThanZero = false;
    private boolean lessThanZero = false;
    private TestInfo testInfo;
    private TestReporter testReporter;


    @BeforeEach
    void init(TestInfo testInfo, TestReporter testReporter){
        calculator = new Calculator();
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }


    @Nested
    @DisplayName("Sum methods together")
    class  Sum {
        @Tag("Sum")
        @Test
        @DisplayName("Sum Positive Test")
        void sumTestPositive() {
            int expected = 30;
            int actual = calculator.getSum(17,13);
            assertEquals(expected, actual,"add method is adding two numbers correctly");
        }

        @Test
        @DisplayName("Sum Negative Test")
        void sumTestNegative() {
            int expected = 10;
            int actual = calculator.getSum(7,5);
            assertEquals(expected,actual);
        }

        @Test
        @DisplayName("If Sum More Than Zero Test")
        void sumTestMoreThanZero() {
            int zero = 0;
            int actual = calculator.getSum(-2, 3);

            if (actual >= zero){
                moreThanZero = true;
            }

            assertTrue(moreThanZero, "Positive number ");
        }

        @Test
        @DisplayName("If Sum Less Than Zero Test")
        void sumTestLessThanZero(){
            int zero = 0;
            int actual = calculator.getSum(-9, 3);
            if (actual <= zero){
                lessThanZero = true;
            }

            assertTrue(lessThanZero, "Negative number ");
        }
    }


    @Test
    @EnabledOnOs(OS.WINDOWS)
    @DisplayName("Division To Zero Test")
    void divideTest(){
        assertThrows(ArithmeticException.class, () -> calculator.divide(1,0), "Can not be divided by zero");
    }

    @Test
    @DisplayName("Fail Test")
    @Disabled
    void testDisabled() {
        fail("This should fail and better to make disabled it");
    }

    @Test
    @DisplayName("Booleans With Assumptions")
    void sumLessThanZeroByAssumption(){
        String displayName = testInfo.getDisplayName();
        Optional<Class<?>> testClass = testInfo.getTestClass();
        Optional<Method> testMethod = testInfo.getTestMethod();
        testReporter.publishEntry("Running" + displayName + "with testClass" + testClass + "and with method" + testMethod);
    }

    @Test
    @DisplayName("booleans with assumptions")
    void sumMoreThanZeroByAssumption() {
        assumingThat(moreThanZero, () -> System.out.println("TRUE"));
    }

    @RepeatedTest(6)
    @DisplayName("Multiply")
    void multiply(){
        assertAll(
                () -> assertEquals(4, calculator.multiply(2,2)),
                () -> assertEquals(9, calculator.multiply(3,3)),
                () -> assertEquals(35, calculator.multiply(5,7)),
                () -> assertEquals(16, calculator.multiply(8,2))
        );
    }
}
