package com.yz.并发编程艺术.chapter07;import java.util.concurrent.atomic.AtomicIntegerArray;/** *  * @author tengfei.fangtf * @version $Id: AtomicIntegerArrayTest.java, v 0.1 2015-8-1 ����12:04:44 tengfei.fangtf Exp $ */public class AtomicIntegerArrayTest {    static int[]              value = new int[] { 1, 2 };    static AtomicIntegerArray ai    = new AtomicIntegerArray(value);    public static void main(String[] args) {        ai.getAndSet(0, 3);        System.out.println(ai.get(0));        System.out.println(value[0]);    }}