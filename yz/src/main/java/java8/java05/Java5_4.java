package java8.java05;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Java5_4 {
    public static void main(String[] args) {
        //元素就和
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }

        //Lambda反复结合每个元素，直到流被归约成一个值
        int sum2 = numbers.stream().reduce(0, (a, b) -> a + b);

        //采用方法引用
        int sum3 = numbers.stream().reduce(0, Integer::sum);

        //reduce还有一个重载的变体，它不接受初始值，但是会返回一个Optional对象：
        Optional<Integer> sum4 = numbers.stream().reduce((a, b) -> (a + b));
        Optional<Integer> sum5 = numbers.stream().reduce(Integer::sum);

        //计算最大值
        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        //计算最小值
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        //怎样用map和reduce方法数一数流中有多少个数据呢
        //map和reduce的连接通常称为map-reduce模式
        //使用流来对所有的元素并行求和时，你的代码几乎不用修改：stream()૰成了parallelStream()。
        int count = numbers.stream().map(d -> 1).reduce(0, Integer::sum);
        long count2 = numbers.stream().count();

        int sum1 = numbers.parallelStream().reduce(0, Integer::sum);
    }
}
