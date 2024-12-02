package me.calrl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DayTwo {

    public static int safeCount = 0;

    public static List<String> getInput() throws IOException {
        Path path = Path.of("DayTwo.txt");
        return Files.readAllLines(path);
    }

    public static void main(String[] args) throws IOException {
        //solutionOne();
        solutionTwo();
    }

    public static List<List<Integer>> convertToList(List<String> input) {
        List<List<Integer>> listOfLists = new ArrayList<>();

        for (String strings : input) {
            String[] nums = strings.split(" ");
            List<Integer> subList = new ArrayList<>();
            for (String num : nums) {
                subList.add(Integer.parseInt(num));
            }
            listOfLists.add(subList);

        }


        return listOfLists;
    }

    public static void solutionOne() throws IOException {
        List<List<Integer>> listOfLists = convertToList(getInput());
        for (List<Integer> list : listOfLists) {
            boolean safe = checkSafe(list);
            if (safe) {
                safeCount += 1;
            }
        }

        System.out.println(safeCount);
    }

    public static void solutionTwo() throws IOException {
        List<List<Integer>> listOfLists = convertToList(getInput());
        for (List<Integer> list : listOfLists) {
            boolean safe = isSafeWithDampener(list);
            if (safe) {
                safeCount += 1;
            }
        }

        System.out.println(safeCount);
    }

    // Increasing/Decreasing = I/D
    // I/D by 1, 2, 3 = safe
    // I/D by more than 3 = unsafe
    // Increasing and decreasing in one list = unsafe
    public static boolean checkSafe(List<Integer> input) {
        for (int i = 0; i < input.size() - 1; i++) {
            int inc = input.get(i + 1) - input.get(i);
            List<Integer> pos = new ArrayList<>(Arrays.asList(1, 2, 3));
            List<Integer> neg = new ArrayList<>(Arrays.asList(-1, -2, -3));

            boolean decreasing = input.get(i) > input.get(i + 1);
            for (int j = 1; j < input.size(); j++) {
                if (Math.abs(input.get(j) - input.get(j - 1)) > 3) {
                    return false;
                }
                if (input.get(j) > input.get(j - 1) && decreasing) {
                    return false;
                }
                if (input.get(j) < input.get(j - 1) && !decreasing) {
                    return false;
                }
                if (input.get(j) == input.get(j - 1)) {
                    return false;
                }

            }
        }
        return true;
    }


    public static boolean isDecreasing(List<Integer> input) {
        for (int i = 1; i < input.size(); i++) {
            if (input.get(i) >= input.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSafeWithDampener(List<Integer> input) {
        if (checkSafe(input)) {
            return true;
        }

        for (int i = 0; i < input.size(); i++) {
            List<Integer> modifiedList = new ArrayList<>(input);
            modifiedList.remove(i);
            if(checkSafe(modifiedList)) {
                return true;
            }
        }
        return false;
    }
}