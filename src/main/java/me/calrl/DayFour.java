package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.Arrays.stream;

public class DayFour {


    private static int[][] directions = {
                { -1, 0 }, // N
                { -1, 1 }, // NE
                { 0, 1 }, // E
                { 1, 1 }, // SE
                { 1, 0 }, // S
                { 1, -1 }, // SW
                { 0, -1 }, // W
                { -1, -1 } // NW
        };
    private static char[][] grid;
    private static final String word = "XMAS";
    private static int count = 0;


    public void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Path.of("inputs/DayFour.txt"));
        grid = listToGrid(input);
        //printGrid(grid);
        //solutionOne(input);
        solutionTwo(grid);
    }


    private static char[][] listToGrid(List<String> input) {
        char[][] grid = new char[input.size()][input.getFirst().length()];
        for(int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }

        return grid;
    }


    private static void solutionOne(List<String> input) {
        //System.out.println(input);
        int count = searchForWord(grid, word);
            System.out.println(count);
    }

    private static void printGrid(char[][] grid) {
        for(char[] row : grid) {
            for(char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    private static int searchForWord(char[][] grid, String word) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col <cols; col++) {
                if(grid[row][col] == word.charAt(0)) {
                    for(int[] direction : directions) {
                        if(searchInDirection(grid, word, row, col, direction[0], direction[1])) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static boolean searchInDirection(char[][] grid, String word, int row, int col, int rowDir, int colDir) {
        int wordLength = word.length();
        int rows = grid.length;
        int cols = grid[0].length;

        for(int i = 0; i < wordLength; i++) {
            int newRow = row + i * rowDir;
            int newCol = col + i * colDir;

            if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            if(grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMAS(char[][] grid, int startRow, int startCol, int rowDir, int colDir) {
        String mas = "MAS";
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < 3; i++) {
            int row = startRow + i * rowDir;
            int col = startCol + i * colDir;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                return false;
            }
        }

        return (grid[startRow][startCol] == 'M' &&
                grid[startRow + rowDir][startCol + colDir] == 'A' &&
                grid[startRow + 2 * rowDir][startCol + 2 * colDir] == 'S')
                ||
                (grid[startRow][startCol] == 'S' &&
                        grid[startRow + rowDir][startCol + colDir] == 'A' &&
                        grid[startRow + 2 * rowDir][startCol + 2 * colDir] == 'M');
    }

    private static boolean isXMAS(char[][] grid, int row, int col) {
        if (onEdge(row, col)) {
            return false;
        }

        boolean forward1 = isMAS(grid, row - 1, col - 1, 1, 1);
        boolean backward1 = isMAS(grid, row - 1, col - 1, -1, -1);
        boolean forward2 = isMAS(grid, row - 1, col + 1, 1, -1);
        boolean backward2 = isMAS(grid, row - 1, col + 1, -1, 1);

        return (forward1 && backward2) || (forward2 && backward1);
    }


    private static int solutionTwo(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (grid[row][col] == 'A' && isXMAS(grid, row, col)) {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    private static boolean onEdge(int row, int col) {
        return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
    }
}