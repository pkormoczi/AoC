package dev.ronin;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.BDDAssertions.then;

class TestDay01 {

    @Test
    void partOne() {
        var lines =new FileFromClassPath("01/input.txt").getLines();
//        var lines =new FileFromClassPath("01/input_sample_1.txt").getLines();
        then(lines.size()).isGreaterThan(0);
//        lines.stream().forEach(System.out::println);
        var listOne = new ArrayList<Integer>();
        var listTwo = new ArrayList<Integer>();
        for (String line : lines) {
            final String[] pairs = line.split("   ");
            listOne.add(Integer.parseInt(pairs[0]));
            listTwo.add(Integer.parseInt(pairs[1]));
        }
        Integer expectedDistance = 11;
        Integer calcDistance = 0;

        Collections.sort(listOne);
        Collections.sort(listTwo);
        System.out.println(listOne);
        System.out.println(listTwo);

        for (int i = 0; i < listOne.size(); i++) {
            calcDistance += Math.abs(listOne.get(i) - listTwo.get(i));
        }
        System.out.println(calcDistance);
//        then(calcDistance).isEqualTo(expectedDistance);


    }

    @Test
    void partTwo() {
        var lines =new FileFromClassPath("01/input.txt").getLines();
//        var lines =new FileFromClassPath("01/input_sample_2.txt").getLines();
        then(lines.size()).isGreaterThan(0);
//        lines.stream().forEach(System.out::println);
        var listOne = new ArrayList<Integer>();
        var listTwo = new ArrayList<Integer>();
        lines.stream().map(line -> line.split("   ")).forEach(pairs -> {
            listOne.add(Integer.parseInt(pairs[0]));
            listTwo.add(Integer.parseInt(pairs[1]));
        });
        Integer expected = 31;
        Integer calculated = 0;

//        System.out.println(listOne);
//        System.out.println(listTwo);

        for (Integer i : listOne) {
            Integer count = Math.toIntExact(listTwo.stream().filter(x -> x.equals(i)).count());
            calculated += i*count;
        }
        System.out.println(calculated);
//        then(calculated).isEqualTo(expected);


    }
}