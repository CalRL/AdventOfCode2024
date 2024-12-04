package me.calrl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DayFour {

    private static int[][] directions = {
            { -1, 0 }, // N
            { -1, 1 }, // NE
            { 0, 1 },  // E
            { 1, 1 },  // SE
            { 1, 0 },  // S
            { 1, -1 }, // SW
            { 0, -1 }, // W
            { -1, -1 } // NW
    };
    private static char[][] grid;

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Path.of("inputs/DayFour.txt"));
        grid = listToGrid(input);
        solutionTwo(grid);
    }

    private static char[][] listToGrid(List<String> input) {
        char[][] grid = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        return grid;
    }

    private static int solutionTwo(char[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] highlight = new boolean[rows][cols];

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (grid[row][col] == 'A' && isXMAS(grid, row, col, highlight)) {
                    count++;
                }
            }
        }
        printGridWithDots(grid, highlight);
        System.out.println(count);
        return count;
    }

    private static boolean isXMAS(char[][] grid, int row, int col, boolean[][] highlight) {
        List<int[]> diag1Positions = isMAS(grid, row - 1, col - 1, 1, 1);
        List<int[]> diag2Positions = isMAS(grid, row - 1, col + 1, 1, -1);

        if (diag1Positions != null && diag2Positions != null) {
            for (int[] pos1 : diag1Positions) {
                for (int[] pos2 : diag2Positions) {
                    if (pos1[0] == row && pos1[1] == col && pos2[0] == row && pos2[1] == col) {
                        highlight[row][col] = true; // Central 'A'
                        for (int[] pos : diag1Positions) {
                            highlight[pos[0]][pos[1]] = true;
                        }
                        for (int[] pos : diag2Positions) {
                            highlight[pos[0]][pos[1]] = true;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private static List<int[]> isMAS(char[][] grid, int startRow, int startCol, int rowDir, int colDir) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<int[]> positions = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            int row = startRow + i * rowDir;
            int col = startCol + i * colDir;

            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                return null;
            }
            positions.add(new int[]{row, col});
        }

        boolean isMAS = grid[startRow][startCol] == 'M' &&
                grid[startRow + rowDir][startCol + colDir] == 'A' &&
                grid[startRow + 2 * rowDir][startCol + 2 * colDir] == 'S';

        boolean isSAM = grid[startRow][startCol] == 'S' &&
                grid[startRow + rowDir][startCol + colDir] == 'A' &&
                grid[startRow + 2 * rowDir][startCol + 2 * colDir] == 'M';

        if (isMAS || isSAM) {
            return positions;
        } else {
            return null;
        }
    }

    private static void printGridWithDots(char[][] grid, boolean[][] highlight) {
        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (highlight[row][col]) {
                    System.out.print(grid[row][col] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
