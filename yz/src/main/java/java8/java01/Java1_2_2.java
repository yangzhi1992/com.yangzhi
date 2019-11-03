package java8.java01;

import java8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Java1_2_2 {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList();
        filterApples(inventory, Apple::isGreenApple);
        filterApples(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return Apple.isGreenApple(apple);
            }
        });

        filterApples(inventory, Apple::isHeavyApple);
        filterApples(inventory, new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return Apple.isHeavyApple(apple);
            }
        });
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

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }
}
