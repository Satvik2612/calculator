package com.calc.calculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {

    /**
     * Adds numbers provided in a string format.
     *
     * <p>This method takes a string input containing numbers separated by delimiters and returns their sum.
     * The input can contain numbers separated by commas (`,`), new lines (`\n`), or a custom delimiter
     * specified at the beginning of the string in the format: "//[delimiter]\n[numbers…]".</p>
     * <p>
     *
     * If the input contains negative numbers, the method throws an IllegalArgumentException with a
     * message indicating the negative numbers found.
     *
     * @param numbers A string containing numbers separated by delimiters.
     * @return The sum of the numbers.
     * @throws IllegalArgumentException If the input contains negative numbers.
     */
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String[] tokens = getTokens(numbers);

        List<Integer> negatives = new ArrayList<>();
        int sum = 0;

        for (String token : tokens) {
            if (!token.isEmpty()) {
                int num = Integer.parseInt(token);
                if (num < 0) {
                    negatives.add(num);
                } else {
                    sum += num;
                }
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }

        return sum;
    }

    /**
     * Splits the input string into an array of number tokens based on delimiters.
     *
     * <p>This method handles both default delimiters (comma and new line) and custom delimiters
     * specified at the beginning of the string in the format "//[delimiter]\n".
     * If a custom delimiter is found, it overrides the default delimiters.</p>
     *
     * @param numbers The input string containing numbers and delimiters.
     * @return An array of number tokens as strings.
     */
    private static String[] getTokens(String numbers) {
        String delimiter = "[,\n]";
        String numStr = numbers;

        // Check if there's a custom delimiter
        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEnd);
            delimiter = delimiter.replaceAll("\\Q[[\\]]\\E", "");
            numStr = numbers.substring(delimiterEnd + 1);
        }

        return numStr.split(delimiter);
    }
}
