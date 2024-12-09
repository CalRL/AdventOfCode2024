package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day05 {

    public static void main(String[] args) throws IOException {
        List<String> pairsList = Files.readAllLines(Path.of("inputs/Day5.txt"));
        List<String> numbers = Files.readAllLines(Path.of("inputs/2Day5.txt"));

        solutionOne(getUpdates(numbers), getRules(pairsList));
        solutionTwo(getUpdates(numbers), getRules(pairsList));
    }


    public static List<int[]> getRules(List<String> pairsList) {
        Map<Integer, Integer> map = new HashMap<>();
        String[] pair;
        Integer one, two;
        List<int[]> rules = new ArrayList<>();
        for(int i = 0; i < pairsList.size(); i++) {
            String[] parts = pairsList.get(i).split("\\|");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            rules.add(new int[]{x, y});
        }

        return rules;
    }

    public static List<List<Integer>> getUpdates(List<String> input) {
        List<List<Integer>> listOfLists = new ArrayList<>();
        for(int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(",");
            List<Integer> updates = new ArrayList<>();
            for(int j = 0; j < parts.length; j++) {
                updates.add(Integer.parseInt(parts[j]));
            }
            listOfLists.add(updates);
        }
        return listOfLists;
    }


    public static void solutionOne(List<List<Integer>> updates, List<int[]> rules) {
        int sum = 0;

        for(List<Integer> update : updates) {
            if(isValidUpdate(update, rules)) {
                int middleIndex = update.size() / 2;
                sum += update.get(middleIndex);
            }
        }

        System.out.println(sum);
    }

    public static void solutionTwo(List<List<Integer>> updates, List<int[]> rules) {
        int sum = 0;

        for(List<Integer> update : updates) {
            Collections.sort(update, Collections.reverseOrder());
            if(!isValidUpdate(update, rules)) {
                int middleIndex = update.size() / 2;
                sum += update.get(middleIndex);
            }
        }

        System.out.println(sum);
    }
    

    public static boolean isValidUpdate(List<Integer> update, List<int[]> rules) {
        Map<Integer, Integer> positionMap = new HashMap<>();
        for(int i = 0; i <update.size(); i++) {
            positionMap.put(update.get(i), i);
        }

        for(int[] rule : rules) {
            int x = rule[0];
            int y = rule[1];

            if(positionMap.containsKey(x) && positionMap.containsKey(y)) {
                if(positionMap.get(x) >= positionMap.get(y)) {
                    return false;
                }
            }


        }
        return true;
    }
}
