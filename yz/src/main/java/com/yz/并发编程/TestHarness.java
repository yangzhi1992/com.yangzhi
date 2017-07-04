package com.yz.并发编程;

import java.util.concurrent.*;

/**
 * TestHarness
 * <p/>
 * Using CountDownLatch for starting and stopping threads in timing tests
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TestHarness {
	
	public static void main(String[] args) throws InterruptedException {
		TestHarness th = new TestHarness();
		th.timeTasks(1 , new Runnable(){
			@Override
			public void run() {
				System.out.println("run");
			}
		});
	}
	
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }

        long start = System.nanoTime();
        System.out.println("start:"+start);
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("end:"+end);
        System.out.println("result:"+(end - start));
        return end - start;
    }
}
