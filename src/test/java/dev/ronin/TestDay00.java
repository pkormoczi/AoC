package dev.ronin;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class TestDay00 {

    final String inputPath = this.getClass().getSimpleName().substring(7) + "/input.txt";
    final String inputPath_Sample_1 = this.getClass().getSimpleName().substring(7) + "/input_sample_1.txt";
    final String inputPath_Sample_2 = this.getClass().getSimpleName().substring(7) + "/input_sample_2.txt";

    @Test
    void part1() {
        var input = new FileFromClassPath(inputPath).getLines();
        then(input.size()).isGreaterThan(0);

    }

    @Test
    void part2() {
        var input = new FileFromClassPath(inputPath).getLines();
        then(input.size()).isGreaterThan(0);
    }
}