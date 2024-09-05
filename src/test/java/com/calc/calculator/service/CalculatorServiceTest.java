package com.calc.calculator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculator = new CalculatorService();

    @Test
    void testAdd_EmptyString_ReturnsZero() {
        assertEquals(0, calculator.add(""), "Adding an empty string should return 0.");
    }

    @Test
    void testAdd_SingleNumber_ReturnsTheNumberItself() {
        assertEquals(5, calculator.add("5"), "Adding a single number should return the number itself.");
    }

    @Test
    void testAdd_TwoNumbersSeparatedByComma_ReturnsTheirSum() {
        assertEquals(3, calculator.add("1,2"), "Adding '1,2' should return 3.");
    }

    @Test
    void testAdd_MultipleNumbersSeparatedByCommas_ReturnsTheirSum() {
        assertEquals(10, calculator.add("1,2,3,4"), "Adding '1,2,3,4' should return 10.");
    }

    @Test
    void testAdd_NumbersSeparatedByNewLineAndComma_ReturnsTheirSum() {
        assertEquals(6, calculator.add("1\n2,3"), "Adding '1\\n2,3' should return 6.");
    }

    @Test
    void testAdd_CustomDelimiterSpecified_ReturnsTheirSum() {
        assertEquals(3, calculator.add("//;\n1;2"), "Adding with custom delimiter ';' should return 3.");
    }

    @Test
    void testAdd_CustomDelimiterWithDifferentCharacter_ReturnsTheirSum() {
        assertEquals(6, calculator.add("//-\n1-2-3"), "Adding with custom delimiter '-' should return 6.");
    }

    @Test
    void testAdd_NegativeNumbers_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("1,-2,3,-4"));
        assertEquals("Negative numbers not allowed: [-2, -4]", exception.getMessage(),
                "Adding negative numbers should throw an exception.");
    }

    @Test
    void testAdd_CustomDelimiterWithBrackets_ReturnsTheirSum() {
        assertEquals(10, calculator.add("//[***]\n1***2***3***4"), "Adding with custom delimiter '[***]' should return 10.");
    }
}