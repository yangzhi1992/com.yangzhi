package java8.java06;

import com.alibaba.fastjson.JSONObject;
import java8.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java6_2 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;

        //计数
        long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishes2 = menu.stream().count();
        long howManyDishes3 = menu.stream().map(t -> 1).reduce(0, Integer::sum);

        //查找流中最大值和最小值
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish =
                menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));

        Optional<Dish> mostCalorieDish2 =
                menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

        Optional<Integer> ss = menu.stream().map(t -> t.getCalories()).reduce(Integer::max);

        //汇总
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        int totalCalories2 = menu.stream().map(t -> t.getCalories()).reduce(0, Integer::sum);
        int totalCalories3 = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        //计算平均值
        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        //sum,avg,min,max
        IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        //连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        String shortMenu2 = menu.stream().map(Dish::getName).reduce("", String::concat);

        //
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<Integer>(),
                (List<Integer> l, Integer e) -> {
                    l.add(e);
                    return l;
                },
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                });

        System.out.println(JSONObject.toJSONString(numbers));

        //查找每个组中热量最高的Dish
        Map<Dish.Type, Dish> mostCaloricByType =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));

        Map<Dish.Type, Optional<Dish>> mostCaloricByType2 =
                menu.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));

        //查找每个组中的热量和
        Map<Dish.Type, Integer> totalCaloriesByType =
                menu.stream().collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));


        Map<Dish.Type, Long> typesCount = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.counting()));

    }
}
