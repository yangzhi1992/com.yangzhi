package java8.java03;

import java8.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Java3_7 {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        //1.
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        //2.
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //3.
        Comparator<Apple> comparator = Comparator.comparing((Apple apple) -> apple.getWeight());
        inventory.sort(comparator);

        //4.
        Comparator<Apple> comparator2 = Comparator.comparing((Apple::getWeight));
        inventory.sort(comparator2);

        //5.逆序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        //6.比较器链 两个苹果按照重量递减排序，两个苹果一样重时，进一步按照颜色排序
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
    }
}
