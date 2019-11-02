package java8.java02;

import java8.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Java2_4 {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();

        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort((Apple o1, Apple o2) -> o1.getWeight().compareTo(o2.getWeight()));

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("yangzhi");
            }
        });

        Thread t2 = new Thread(() -> System.out.println("yangzhi"));

    }
}
