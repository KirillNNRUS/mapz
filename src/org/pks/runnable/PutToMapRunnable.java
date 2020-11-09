package org.pks.runnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class PutToMapRunnable implements Runnable {
    final Map<String, String> map;
    final CountDownLatch countDownLatch;
    final List<String> strings;
    final int myInt;

    public PutToMapRunnable(Map<String, String> map, CountDownLatch countDownLatch, List<String> strings, int myInt) {
        this.map = map;
        this.countDownLatch = countDownLatch;
        this.strings = strings;
        this.myInt = myInt;
    }

    @Override
    public void run() {
        map.put(strings.get(myInt), strings.get(myInt));
        countDownLatch.countDown();
    }
}
