package java8.java03;

import java8.Apple;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.ToIntBiFunction;

public class Java3_5 {

    public static void main(String[] args) {
        Comparable<Apple> c1 = (Apple::getWeight);

        Comparator<Apple> c2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        ToIntBiFunction<Apple, Apple> c3 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

        BiFunction<Apple, Apple, Integer> c4 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }
}
