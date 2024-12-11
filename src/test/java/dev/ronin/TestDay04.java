package dev.ronin;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class TestDay04 {

    final String inputPath = this.getClass().getSimpleName().substring(7) + "/input.txt";
    final String inputPath_Sample_1 = this.getClass().getSimpleName().substring(7) + "/input_sample_1.txt";
    final String inputPath_Sample_2 = this.getClass().getSimpleName().substring(7) + "/input_sample_2.txt";
    public char[][] matrix;
    int COL;
    int ROW;

    @Test
    void part1() {
//        var input = new FileFromClassPath(inputPath_Sample_1).getLines();
        var input = new FileFromClassPath(inputPath).getLines();
        then(input.size()).isGreaterThan(0);

        var actual = solvePart1(input);
        System.out.println("RESULT: " + actual);

//        var expected = 18;
//        then(actual).isEqualTo(expected);

    }

    @Test
    void part2() {
//        var input = new FileFromClassPath(inputPath_Sample_2).getLines();
        var input = new FileFromClassPath(inputPath).getLines();
        then(input.size()).isGreaterThan(0);

        var actual = solvePart2(input);
        System.out.println("RESULT: " + actual);

//        var expected = 9;
//        then(actual).isEqualTo(expected);
    }

    private int solvePart2(List<String> input) {
        int count = 0;
        COL = input.getFirst().length();
        ROW = input.size();
        matrix = new char[ROW][COL];
        int r = 0;
        for (String line : input) {
            matrix[r] = line.toCharArray();
            r++;
        }

        printMatrix();

        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (matrix[row][col] == 'A') {
                    System.out.print("A at " + row + "," + col);
                    try {
                        if (((matrix[row - 1][col - 1] == 'M' && matrix[row + 1][col + 1] == 'S') || (matrix[row - 1][col - 1] == 'S' && matrix[row + 1][col + 1] == 'M'))
                            &&
                            ((matrix[row - 1][col + 1] == 'M' && matrix[row + 1][col - 1] == 'S') || (matrix[row - 1][col + 1] == 'S' && matrix[row + 1][col - 1] == 'M'))
                        ) {
                            count++;
                        }
                    } catch (Exception e) {
                        continue;
                    }
                    System.out.println();
                }
            }
        }

        return count;
    }

    private int solvePart1(List<String> input) {
        int count = 0;
        COL = input.getFirst().length();
        ROW = input.size();
        matrix = new char[ROW][COL];
        int r = 0;
        for (String line : input) {
            matrix[r] = line.toCharArray();
            r++;
        }
//        printMatrix();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                if (matrix[row][col] == 'X') {
                    System.out.print("X at " + row + "," + col);
                    //search ➡️
                    if (col + 4 <= COL && String.valueOf(matrix[row]).startsWith("XMAS", col)) {
                        System.out.print("➡\uFE0F");
                        count++;
                    }
                    //search ⬅️
                    if (3 <= col && String.valueOf(matrix[row]).substring(col - 3, col + 1).equals("SAMX")) {
                        System.out.print("⬅\uFE0F");
                        count++;
                    }
                    //search ⬇️
                    if (row <= ROW - 3 && getColumn(col).startsWith("XMAS", row)) {
                        System.out.print("⬇\uFE0F");
                        count++;
                    }
                    //search⬆️
                    if (3 <= row && getColumn(col).substring(row - 3, row + 1).equals("SAMX")) {
                        System.out.print("⬆\uFE0F");
                        count++;
                    }
                    //search ↗️
                    if (col + 4 <= COL && 3 <= row) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            builder.append(matrix[row - i][col + i]);
                        }
                        if (builder.toString().equals("XMAS")) {
                            System.out.print("↗\uFE0F");
                            count++;
                        }
                    }
                    //search ↘️
                    if (col + 4 <= COL && row + 4 <= ROW) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            builder.append(matrix[row + i][col + i]);
                        }
                        if (builder.toString().equals("XMAS")) {
                            System.out.print("↘\uFE0F");
                            count++;
                        }
                    }
                    //search ↖️
                    if (3 <= col && 3 <= row) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            builder.append(matrix[row - i][col - i]);
                        }
                        if (builder.toString().equals("XMAS")) {
                            System.out.print("↖\uFE0F");
                            count++;
                        }
                    }
                    //search ↙️
                    if (3 <= col && row + 4 <= ROW) {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            builder.append(matrix[row + i][col - i]);
                        }
                        if (builder.toString().equals("XMAS")) {
                            System.out.print("↙\uFE0F");
                            count++;
                        }
                    }
                    System.out.println();
                }
            }
        }
        return count;
    }

    private String getColumn(int col) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            if (col < matrix[i].length) {
                result.append(matrix[i][col]);
            }
        }
        return result.toString();
    }

    private void printMatrix() {
        System.out.print(" \t");
        for (int i = 0; i < COL; i++) {
            System.out.print(i + " \t");
        }
        System.out.println();
        for (int i = 0; i < ROW; i++) {
            System.out.print(i + " \t");
            for (int j = 0; j < COL; j++) {
                System.out.print(matrix[i][j] + " \t");
            }
            System.out.println();
        }
    }
}