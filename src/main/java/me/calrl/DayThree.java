package me.calrl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("DayThree.txt");
        String file = Files.readString(path);
        //System.out.println(file);
        CharSequence cs = file;
        solutionTwo(cs);
    }

    private static List<String> convertToList(CharSequence input, Pattern pattern) {
        List<String> listOfStrings = new ArrayList<>();
        //Pattern pattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            listOfStrings.add(matcher.group());
            //System.out.println(matcher.group());
        }

        return listOfStrings;
    }

    private static void solutionOne(CharSequence input) {
        int sum = 0;
        Pattern pattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))");
        List<String> listOfStrings = convertToList(input, pattern);
        for(int i = 0; i < listOfStrings.size(); i++) {
            String string = listOfStrings.get(i);
            string = string.replace("mul(", "");
            string = string.replace(")", "");
            String[] strings = string.split(",");

            int first = Integer.parseInt(strings[0]);
            int second = Integer.parseInt(strings[1]);

            sum += first * second;

        }
        System.out.println(sum);
    }

    private static void solutionTwo(CharSequence input) {
        Pattern pattern = Pattern.compile("(do\\(\\))|(mul\\(\\d{1,3},\\d{1,3}\\))|(don't\\(\\))");
        List<String> listOfStrings = convertToList(input, pattern);
        int sum = 0;
        boolean dont = false;
        boolean ignore = true;
        for(int i = 0; i < listOfStrings.size(); i++) {
            String string = listOfStrings.get(i);
            if(Objects.equals(string, "don't()")) {
                dont = true;
                ignore = false;
            } else if(Objects.equals(string, "do()")) {
                dont = false;
                ignore = false;
            } else {
                ignore = true;
            }
            if(!dont && ignore) {

                string = string.replace("mul(", "");
                string = string.replace(")", "");
                String[] strings = string.split(",");

                int first = Integer.parseInt(strings[0]);
                int second = Integer.parseInt(strings[1]);

                sum += first * second;
            }
        }
        System.out.println(sum);
    }


}
