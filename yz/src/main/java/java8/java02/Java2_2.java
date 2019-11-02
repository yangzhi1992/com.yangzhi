package java8.java02;

import java8.Apple;

import java.util.ArrayList;
import java.util.List;

public class Java2_2 {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        ApplePredicate applePredicateGreen = new AppleGreenColorPredicate();
        List<Apple> greenApples = filterApples(inventory, applePredicateGreen);

        ApplePredicate applePredicateWeight = new AppleHeavyWeightPredicate();
        List<Apple> weightApples = filterApples(inventory, applePredicateWeight);

        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });

        List<Apple> redApples2 = filterApples(inventory, (Apple a) -> "red".equals(a.getColor()));

        List<Apple> redApples3 = filter(inventory, (Apple a) -> "red".equals(a.getColor()));
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory,
                                           ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
