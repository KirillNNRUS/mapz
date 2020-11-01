package org.pks;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /*
        На 100 потоках мой Map даже выигрывает :-) Потом видимо все съедает array copy
        Сначала я сделал запуск потоков через Executors.newCachedThreadPool();
        покажу, что я умный) и... Не мог понять... почему у меня размер Map
        меньше чем количество потоков...
        То, что пишу Имя потока и т.к. потоки переиспользуются и в результате
        опять пишу, то же имя понял не сразу...


         */
        int maxThread = 100;
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        Map<String, String> arrayMap = new ArrayListsMap<>();

        out.println(arrayMap);

        Instant start = Instant.now();
        for (int i = 0; i < maxThread; i++) {
            Thread thread = new Thread(new PutToMapRunnable(arrayMap, countDownLatch));
            thread.start();
        }

        countDownLatch.await();

        out.println(arrayMap.size());

        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        out.println("Прошло времени, мс: " + elapsed);

        countDownLatch = new CountDownLatch(maxThread);

        Map<String, String> map = new ConcurrentHashMap<>();

        out.println(map);

        start = Instant.now();
        for (int i = 0; i < maxThread; i++) {
            Thread thread = new Thread(new PutToMapRunnable(map, countDownLatch));
            thread.start();
        }

        countDownLatch.await();

        out.println(map.size());
        finish = Instant.now();
        elapsed = Duration.between(start, finish).toMillis();
        out.println("Прошло времени, мс: " + elapsed);
    }
}
