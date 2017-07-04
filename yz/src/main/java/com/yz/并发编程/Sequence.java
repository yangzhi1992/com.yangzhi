package com.yz.并发编程;



/**
 * Sequence
 *
 * @author Brian Goetz and Tim Peierls
 */


public class Sequence {
    private int nextValue;

    public synchronized int getNext() {
        return nextValue++;
    }
}
