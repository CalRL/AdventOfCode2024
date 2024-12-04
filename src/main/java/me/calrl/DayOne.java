package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayOne {
    public static int listLength = 4;


    public static void main(String[] args) throws IOException {

        Path path = Path.of("DayOne.txt");
        List<String> file = Files.readAllLines(path);

        //solutionOne(file);
        solutionTwo(file);
    }


    public static void solutionOne(List<String> input) {
        List<List<Integer>> listOfLists = splitLists(input);
        List<Integer> leftList = listOfLists.get(0);
        List<Integer> rightList = listOfLists.get(1);
        int distanceSum = 0;
        for(int i = 0; i < leftList.size(); i++) {
            int min = Math.min(leftList.get(i), rightList.get(i));
            int max = Math.max(leftList.get(i), rightList.get(i));
            int num = max - min;
            distanceSum += num;

        }
        System.out.println(distanceSum);

    }

    public static void solutionTwo(List<String> input) {
        List<List<Integer>> listOfLists = splitLists(input);
        List<Integer> leftList = listOfLists.get(0);
        List<Integer> rightList = listOfLists.get(1);
        int sum = 0;
        for(int i = 0; i<rightList.size(); i++) {
            int frequency = Collections.frequency(rightList, leftList.get(i));
            int product = leftList.get(i) * frequency;
            sum += product;
        }
        System.out.println(sum);
    }

    public static List<List<Integer>> splitLists(List<String> input) {
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for(int i = 0; i < input.size(); i++) {
            String[] splitData = input.get(i).split(" {3}");
            leftList.add(Integer.parseInt(splitData[0]));
            rightList.add(Integer.parseInt(splitData[1]));
        }
        Collections.sort(leftList);
        Collections.sort(rightList);


        List<List<Integer>> returnList = new ArrayList<>();
        returnList.add(leftList);
        returnList.add(rightList);

        return returnList;
    }


}