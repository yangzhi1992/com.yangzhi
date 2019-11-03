package java8.java05;

import java8.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java5_1和2 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;

        //filter：过滤
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());

        //distinct：去掉重复
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        //limit：截断流
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());

        //skip：跳过元素
        List<Dish> dishes2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());

        //map：对流中每个元素应用函数
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());

        words.stream().map(s -> s.split("")).distinct().collect(Collectors.toList());

        words.stream().map(s -> s.split("")).map(Arrays::stream).distinct().collect(Collectors.toList());

        words.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());


        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);


    }
}
