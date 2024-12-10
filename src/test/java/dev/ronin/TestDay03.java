package dev.ronin;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.BDDAssertions.then;

class TestDay03 {

    final String inputPath = this.getClass().getSimpleName().substring(7) + "/input.txt";
    final String inputPath_Sample_1 = this.getClass().getSimpleName().substring(7) + "/input_sample_1.txt";
    final String inputPath_Sample_2 = this.getClass().getSimpleName().substring(7) + "/input_sample_2.txt";

    @Test
    void part1() {
        var lines = new FileFromClassPath(inputPath).getLines();
//        var lines = new FileFromClassPath(inputPath_Sample_1).getLines();
        then(lines.size()).isGreaterThan(0);
        var expected = 161;
        var actual = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");

        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                final int sum = parseInt(matcher.group(1)) * parseInt(matcher.group(2));
                System.out.println(matcher.group(1) + " * " + matcher.group(2) + " = " + sum);
                actual += sum;
            }
        }
        System.out.println(actual);
        then(actual).isEqualTo(expected);

    }

    @Test
    void part2() {
        var lines = new FileFromClassPath(inputPath).getLines();
        then(lines.size()).isGreaterThan(0);
        var actual = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        var doPattern = Pattern.compile("(?:do\\(\\)|don't\\(\\)).*?(?=(?:do\\(\\)|don't\\(\\))|$)");
        for (String line : lines) {
            var doMatcher = doPattern.matcher(line);
            while (doMatcher.find()) {
                var sub = doMatcher.group();
                System.out.println(sub);
                if (sub.contains("do()")) {
                    Matcher matcher = pattern.matcher(sub);
                    while (matcher.find()) {
                        System.out.print(matcher.group() + " --->>>  ");
                        final int sum = parseInt(matcher.group(1)) * parseInt(matcher.group(2));
                        System.out.println(matcher.group(1) + " * " + matcher.group(2) + " = " + sum);
                        actual += sum;
                    }
                }
            }
        }

//        var expected = 48;
        System.out.println(actual);
//        then(actual).isEqualTo(expected);
    }
}