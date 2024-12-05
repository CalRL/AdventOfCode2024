package me.calrl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public static void main(String[] args) throws IOException {
        List<String> pairsList = Files.readAllLines(Path.of("inputs/Day5.txt"));
        List<String> numbers = Files.readAllLines(Path.of("inputs/2Day5.txt"));
        System.out.println(pairsList);
        System.out.println(numbers);
        List<List<Integer>> pairs = new ArrayList<>();
        String[] pair;
        Integer one, two;

        for(int i = 0; i < pairsList.size(); i++) {
            pair = pairsList.get(i).split("\\|");
            one = Integer.parseInt(pair[0]);
            two = Integer.parseInt(pair[1]);
            List<Integer> pairInt = new ArrayList<>();

            pairInt.add(one);
            pairInt.add(two);
            pairs.add(pairInt);
        }
        System.out.println(pairs);
    }

    public static void solutionOne(List<String> input) {

    }

}
