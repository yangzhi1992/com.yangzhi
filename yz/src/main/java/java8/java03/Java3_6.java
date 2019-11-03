package java8.java03;

import com.alibaba.fastjson.JSONObject;
import java8.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Java3_6 {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        inventory.sort(Comparator.comparing(Apple::getWeight));

        List<String> str = Arrays.asList("a", "b", "A", "B");
        str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        str.sort(String::compareToIgnoreCase);

        //构造函数引用指向默认的Apple()构造函数
        Supplier<Apple> c1 = Apple::new;
        Apple apple = c1.get();

        Supplier<Apple> c2 = () -> new Apple();
        Apple apple1 = c2.get();

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, (Integer e) -> new Apple(e, ""));
        System.out.println(JSONObject.toJSONString(apples));

        //指向Apple(Integer weight) 的构造函数引用
        List<Apple> apples2 = map(weights, Apple::new);

        //指向Apple(SString color,Integer weight)的构造函数引用
        BiFunction<String, Integer, Apple> c3 = Apple::new;
        //调用该BiFunction函数的apply方法，并给出要求的颜色和重量，将产生一个新的Apple对象
        Apple c4 = c3.apply("green", 110);
    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }
}
