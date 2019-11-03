package java8.java01;


import java8.Apple;

import java.util.*;
import java.util.stream.Collectors;

public class Java1_3 {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList();

        Map<Curreny2, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1000) {
                Curreny2 currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency =
                        transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency,
                            transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }

        Map<Curreny2, List<Transaction>> transactionsByCurrencies2 =
                transactions.stream()
                        .filter((Transaction t) -> t.getPrice() > 1000)
                        .collect(Collectors.groupingBy(Transaction::getCurrency));

        List<Apple> inventory = new ArrayList<>();
        List<Apple> apples = inventory.stream()
                .filter((Apple apple) -> apple.getWeight() > 100)
                .collect(Collectors.toList());
    }


}
