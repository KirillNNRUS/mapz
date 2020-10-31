package org.pks;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int maxThread = 10_000;
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        Map<String, String> arrayMap = new ArrayListsMap<>();

        out.println(arrayMap);

        for (int i = 0; i < maxThread; i++) {
            Thread thread = new Thread(new MyRunnable(arrayMap, countDownLatch));
            thread.start();
        }

        countDownLatch.await();

        out.println(arrayMap);
        out.println(arrayMap.size());

    }
}
