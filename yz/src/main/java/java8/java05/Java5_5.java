package java8.java05;

import java.util.*;
import java.util.stream.Collectors;

public class Java5_5 {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> transactions1 = transactions.stream()
                .filter(b -> b.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        //交易员都在哪些不同的ۡ市工作过
        List<String> citys = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());

        List<String> citys2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        Set<String> cities =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(Collectors.toSet());

        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> names = transactions.stream()
                .map(t -> t.getTrader())
                .filter(t1 -> "Cambridge".equals(t1.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        //返回所有交易员的ހ名字符串，按字母顺序排序
        String str = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", String::concat);

        System.out.println(str);

        //有没有交易员是在米兰工作的
        boolean milanBased =
                transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan"));

        //打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //所有交易中，最高的交易额是多少
        Optional<Integer> highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);

        //找到交易额最小的交易
        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> smallestTransaction2 =
                transactions.stream()
                        .min(Comparator.comparing((Transaction::getValue)));
    }
}
