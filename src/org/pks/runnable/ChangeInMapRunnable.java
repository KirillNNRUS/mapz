package org.pks.runnable;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class ChangeInMapRunnable implements Runnable {
    final Map<String, String> map;
    final CountDownLatch countDownLatch;
    final List<String> strings;
    final Random random = new Random();

    public ChangeInMapRunnable(Map<String, String> map, CountDownLatch countDownLatch, List<String> strings) {
        this.map = map;
        this.countDownLatch = countDownLatch;
        this.strings = strings;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String change = strings.get(random.nextInt(strings.size()));
        map.put(change, String.valueOf((random.nextInt(10000))));
        countDownLatch.countDown();
    }
}
