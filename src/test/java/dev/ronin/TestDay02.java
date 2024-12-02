package dev.ronin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class TestDay02 {

    final String inputPath = this.getClass().getSimpleName().substring(7) + "/input.txt";
    final String inputPath_Sample_1 = this.getClass().getSimpleName().substring(7) + "/input_sample_1.txt";
    final String inputPath_Sample_2 = this.getClass().getSimpleName().substring(7) + "/input_sample_2.txt";

    @Test
    void partOne() {
        var lines = new FileFromClassPath(inputPath).getLines();
        then(lines.size()).isGreaterThan(0);

        Integer actual = 0;
        for (String line : lines) {
            System.out.print(line + " -> ");
            var levels = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
            var isSafe = true;
            var firstDiff = levels[0] - levels[1] > 0;
            for (int i = 0; i < levels.length - 1; i++) {
                final int diff = levels[i] - levels[i + 1];
                if (diff == 0 || Math.abs(diff) > 3 || (diff > 0 != firstDiff)) {
                    isSafe = false;
                    break;
                }
            }
            if (isSafe) {
                System.out.println("SAFE");
                actual++;
            } else {
                System.out.println("UNSAFE");
            }

        }
        System.out.println(actual.toString());
//        Integer expected = 2;
//        then(actual).isEqualTo(expected);
    }

    @Test
    void partTwo() {
        var lines = new FileFromClassPath(inputPath).getLines();
        then(lines.size()).isGreaterThan(0);

        Integer actual = 0;
        for (String line : lines) {
//            System.out.print(line + " -> ");
            var levels = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).boxed().toList();
            var isSafe = testLine(levels);
            if (isSafe) {
                actual++;
            } else {
                for (var i = 0; i < levels.size(); i++) {
                    var safe = testLine(removeLevel(levels, i));
                    if (safe) {
                        actual++;
                        break;
                    }
                }
            }
        }
        System.out.println(actual);
        Integer expected = 4;
        then(actual).isEqualTo(expected);

    }

    private List<Integer> removeLevel(List<Integer> levels, int level) {
        var newLevels = new ArrayList<>(levels);
        newLevels.remove(level);
        return newLevels;
    }

    private static boolean testLine(List<Integer> levels) {
        System.out.print(levels + " -> ");
        var firstDiff = levels.get(0) - levels.get(1) > 0;
        for (int i = 0; i < levels.size() - 1; i++) {
            final int diff = levels.get(i) - levels.get(i + 1);
            if (diff == 0 || Math.abs(diff) > 3 || (diff > 0 != firstDiff)) {
                System.out.println("UNSAFE");
                return false;
            }
        }
        System.out.println("SAFE");
        return true;
    }
}