package java8.java05;

import java8.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Java5_3 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;

        //检查是否匹配一个元素
        Boolean flag = menu.stream().anyMatch(Dish::isVegetarian);

        //检查是否匹配所有元素
        Boolean flag2 = menu.stream().allMatch(d -> d.getCalories() < 1000);

        //检查是否不匹配>=1000的元素
        Boolean flag3 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);

        //何时用findFirst和findAny
        //你可能会想，为什么会何时有findFirst和findAny呢？答案是并行。找到第一个元素
        //在并行上限制更多。如果你不关心返回的元素是哪个，请使用findAny，因为它在使用并行流
        //时限制较少。

        //查找元素
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny() //返回一个Optional<Dish>
                .ifPresent(d -> System.out.println(d.getName())); //如果包含一个值就打印它，否则什么也不做

        //查找第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst(); // 9
    }
}
