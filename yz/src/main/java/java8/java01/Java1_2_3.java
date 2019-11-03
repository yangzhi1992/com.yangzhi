package java8.java01;

import java8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java1_2_3 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList();
        filterApples(inventory, (Apple apple) -> "grean".equals(apple.getColor()));

        filterApples(inventory, (Apple apple) -> apple.getWeight() > 150);

        filterApples(inventory, (Apple apple) -> apple.getWeight() > 150 || "grean".equals(apple.getColor()));
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           Predicate<Apple> p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
