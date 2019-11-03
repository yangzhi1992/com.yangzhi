package java8.java04;

import java8.Dish;

import java.util.*;
import java.util.stream.Collectors;

public class Java4 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;
        List<Dish> lowCaloricDishes = new ArrayList<>();
        //用累加器刷选元素
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }
        //排序
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        //得出名称
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        List<String> lowCaloricDishesName2 = menu.parallelStream()
                .filter(d -> d.getCalories() < 400) //用过滤器刷选元素
                .sorted(Comparator.comparing(Dish::getCalories)) //排序
                .map(Dish::getName) //得出名称
                .collect(Collectors.toList());

        //为了利用多核架构并行执行这段代码，你只需要把stream()换成parallelStream()：
        List<String> lowCaloricDishesName3 = menu.stream()
                .filter(d -> d.getCalories() < 400) //用过滤器刷选元素
                .sorted(Comparator.comparing(Dish::getCalories)) //排序
                .map(Dish::getName) //得出名称
                .collect(Collectors.toList());

        //安装type分类
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        //
        List<String> lowCaloricDishesName4 = menu.stream()
                .filter(d -> d.getCalories() < 400) //用过滤器刷选元素
                .sorted(Comparator.comparing(Dish::getCalories)) //排序
                .map(Dish::getName) //得出名称
                .limit(5)
                .collect(Collectors.toList());
    }
}
