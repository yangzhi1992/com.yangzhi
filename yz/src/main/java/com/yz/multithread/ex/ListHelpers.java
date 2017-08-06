package com.yz.multithread.ex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.yz.书籍.并发编程实战.NotThreadSafe;
import com.yz.书籍.并发编程实战.ThreadSafe;



/**
 * ListHelder
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of
 * put-if-absent helper methods for List
 *
 * @author Brian Goetz and Tim Peierls
 */

/** 
 * 为什么非线程安全
 * 1：多个线程单个对list操作是线程安全的
 * 2：list是BadListHelper0属性，对于BadListHelper0来说非线程安全
 * 3：当一个线程执行BadListHelper0。putIfAbsent方法时执行到 boolean absent = !list.contains(x);发现该元素不存在即加入
 * 4：另外一个线程执行BadListHelper0。list.add(x)操作是已经完成，这是上面一个线程继续执行加入x
 * 5：程序中有两个x
 * @author hy
 *
 * @param <E>
 */
@NotThreadSafe
class BadListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent)
            list.add(x);
        return absent;
    }
}

/**
 * 保证执行到55到60行之间对list是锁定的
 * @author hy
 *
 * @param <E>
 */
@ThreadSafe
class GoodListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent)
                list.add(x);
            return absent;
        }
    }
}
