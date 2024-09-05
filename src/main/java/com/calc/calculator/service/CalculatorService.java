package com.calc.calculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorService {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = "[,\n]";
        String numStr = numbers;

        // Check if there's a custom delimiter
        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterEnd);
            delimiter = delimiter.replaceAll("\\Q[[\\]]\\E", "");
            numStr = numbers.substring(delimiterEnd + 1);
        }

        String[] tokens = numStr.split(delimiter);
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
}
