package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Day06 {

    public enum directions {
        N(new int[]{-1, 0}),
        E(new int[]{0, 1}),
        S(new int[]{1, 0}),
        W(new int[]{0, -1});

        private final int[] key;
        directions(int[] ints) {
            this.key = ints;
        }

        public int[] getDirection() {
            return key;
        }
    }

    public enum pointers {
        N("^"),
        E(">"),
        W("<"),
        S("v");

        private final String key;
        pointers(String s) {
            this.key = s;
        }

        public String getPointer() {
            return key;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Path.of("inputs/Day06.txt"));
        solutionOne(input);

    }
    public static int sum = 0;
    public static void solutionOne(List<String> input) {
        int distinctPositions = simulatePatrol(getInitialPosition(input), directions.N, input);
        System.out.println(distinctPositions);
    }

    public static int simulatePatrol(int[] position, directions currentDirection, List<String> grid) {
        Set<String> visitedPositions = new HashSet<>();
        visitedPositions.add(position[0] + "," + position[1]); // Add the initial position

        while (true) {
            // Calculate the next position based on the current direction
            int[] nextPosition = getNextPosition(position, currentDirection);

            // Check if the next position is out of bounds
            if (nextPosition[0] < 0 || nextPosition[0] >= grid.size() ||
                    nextPosition[1] < 0 || nextPosition[1] >= grid.get(nextPosition[0]).length()) {
                System.out.println("Out of bounds. Stopping patrol.");
                break;
            }

            // Check if there's an obstacle at the next position
            if (grid.get(nextPosition[0]).charAt(nextPosition[1]) == '#') {
                System.out.println("Obstacle detected at (" + nextPosition[0] + ", " + nextPosition[1] + ")");
                currentDirection = turnRight(currentDirection); // Turn right
                System.out.println("Turning right. New direction: " + currentDirection);

                // Recalculate next position after turning
                nextPosition = getNextPosition(position, currentDirection);
                System.out.println(Arrays.toString(nextPosition));
            }

            // Move to the next position
            System.out.println("Moving from (" + position[0] + ", " + position[1] + ") to (" + nextPosition[0] + ", " + nextPosition[1] + ")");
            position[0] = nextPosition[0];
            position[1] = nextPosition[1];
            visitedPositions.add(position[0] + "," + position[1]); // Track the visited position

            // Print the updated board state
            //printBoard(grid, position, currentDirection);
            System.out.println();
        }

        return visitedPositions.size(); // Return the number of distinct positions visited
    }

    public static int[] getNextPosition(int[] position, directions currentDirection) {
        int[] nextPosition = new int[]{
                position[0] + currentDirection.getDirection()[0],
                position[1] + currentDirection.getDirection()[1]
        };
        return nextPosition;
    }




    public static int[] getInitialPosition(List<String> input) {
        int line;
        int pos;
        for(int i = 0; i < input.size(); i++) {
            int index = input.get(i).indexOf("^");
            if(index != -1) {
                line = i;
                pos = index;
                return new int[]{line, pos};
            }
        }
        System.out.println("Couldn't get initial position");
        return new int[0];
    }

    public static directions turnRight(directions currentDirection) {
        directions newDirection = switch (currentDirection) {
            case N -> directions.E;
            case E -> directions.S;
            case S -> directions.W;
            case W -> directions.N;
        };
        System.out.println("Turning right: " + currentDirection + " -> " + newDirection);
        return newDirection;
    }

    public static void printBoard(List<String> grid, int[] position, directions currentDirection) {
        for (int i = 0; i < grid.size(); i++) {
            StringBuilder row = new StringBuilder(grid.get(i));
            if (i == position[0]) {
                row.setCharAt(position[1], getPointerForDirection(currentDirection)); // Use the guard's pointer
            }
            System.out.println(row);
        }
    }

    private static char getPointerForDirection(directions direction) {
        return switch (direction) {
            case N -> '^';
            case E -> '>';
            case S -> 'v';
            case W -> '<';
        };
    }
}
