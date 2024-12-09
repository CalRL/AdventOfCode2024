package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day07 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("inputs/Day07.txt"));

        System.out.println("Total Calibration Result: " + findValidEquations(input));
    }

    private static long findValidEquations(List<String> input) {
        long total = 0;

        // Split input into lines
        for (String line : input) {
            // Parse the test value and numbers
            String[] parts = line.split(": ");
            long testValue = Long.parseLong(parts[0]);
            String[] numberStrings = parts[1].split(" ");
            long[] numbers = new long[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }

            // Check if any combination of operators produces the test value
            if (canProduceTestValue(numbers, testValue)) {
                total += testValue;
            }
        }

        return total;
    }

    private static boolean canProduceTestValue(long[] numbers, long testValue) {
        int n = numbers.length;
        int operatorSlots = n - 1;

        // Generate all combinations of operators
        List<String[]> operatorCombinations = generateOperatorCombinations(operatorSlots);

        // Test all combinations
        for (String[] operators : operatorCombinations) {
            if (evaluateExpression(numbers, operators) == testValue) {
                return true;
            }
        }

        return false;
    }

    private static List<String[]> generateOperatorCombinations(int slots) {
        List<String[]> combinations = new ArrayList<>();
        int totalCombinations = (int) Math.pow(2, slots);

        for (int i = 0; i < totalCombinations; i++) {
            String[] combination = new String[slots];
            for (int j = 0; j < slots; j++) {
                combination[j] = (i & (1 << j)) == 0 ? "+" : "*";
            }
            combinations.add(combination);
        }

        return combinations;
    }

    private static long evaluateExpression(long[] numbers, String[] operators) {
        long result = numbers[0];

        for (int i = 0; i < operators.length; i++) {
            if (operators[i].equals("+")) {
                result += numbers[i + 1];
            } else if (operators[i].equals("*")) {
                result *= numbers[i + 1];
            }
        }

        return result;
    }
}
