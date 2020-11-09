package org.pks;

import org.pks.runnable.ChangeInMapRunnable;
import org.pks.runnable.DeleteInMapRunnable;
import org.pks.runnable.PutToMapRunnable;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Utils utils = new Utils();

        /*
        На 100 потоках мой Map даже выигрывает :-) Потом видимо все съедает array copy
        Сначала я сделал запуск потоков через Executors.newCachedThreadPool();
        покажу, что я умный) и... Не мог понять... почему у меня размер Map
        меньше чем количество потоков...
        То, что пишу Имя потока и т.к. потоки переиспользуются и в результате
        опять пишу, то же имя понял не сразу...
         */

        ExecutorService es = Executors.newCachedThreadPool();

        int maxThreadToPut = 10;
        int maxThreadToChange = 3;
        int maxThreadToDelete = 3;

        int allThread = maxThreadToPut + maxThreadToDelete + maxThreadToChange;
        CountDownLatch countDownLatch = new CountDownLatch(allThread);

        Set<String> stringSet = new HashSet<>();
        while (stringSet.size() < maxThreadToPut) {
            stringSet.add(utils.generateString(maxThreadToPut));
        }
        List<String> stringList = stringSet.stream().collect(Collectors.toList());


        Map<String, String> arrayMap = new ArrayListsMap<>();

        out.println(arrayMap);

        Instant start = Instant.now();
        for (int i = 0; i < maxThreadToPut; i++) {
            es.submit(new PutToMapRunnable(arrayMap, countDownLatch, stringList, i));
        }

        for (int i = 0; i < maxThreadToChange; i++) {
            es.submit(new ChangeInMapRunnable(arrayMap, countDownLatch, stringList));
        }

        for (int i = 0; i < maxThreadToDelete; i++) {
            es.submit(new DeleteInMapRunnable(arrayMap, countDownLatch, stringList));
        }

        countDownLatch.await();

        out.println(arrayMap);
        out.println(arrayMap.size());

        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        out.println("Прошло времени, мс: " + elapsed);

        es.shutdown();
    }
}
