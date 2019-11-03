package java8.java03;

import java8.Apple;

import java.util.function.Predicate;

public class Java3_8 {
    public static void main(String[] args) {
        Predicate<Apple> redApple = (Apple a) -> true;

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple =
                redApple.and(a -> a.getWeight() > 150);

        Predicate<Apple> redAndHeavyAppleOrGreen =
                redApple.and(a -> a.getWeight() > 150)
                        .or(a -> "green".equals(a.getColor()));
    }
}
