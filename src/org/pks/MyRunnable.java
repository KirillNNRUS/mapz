package org.pks;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MyRunnable implements Runnable {
    final Map<String, String> map;
    final CountDownLatch countDownLatch;

    public MyRunnable(Map<String, String> map, CountDownLatch countDownLatch) {
        this.map = map;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        map.put(Thread.currentThread().getName(), Thread.currentThread().getName());
        countDownLatch.countDown();
    }
}
