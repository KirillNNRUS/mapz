package org.pks;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("one", "one");
        Map<String, String> mapSec = new HashMap<>();
        mapSec.putAll(map);
        Map<String, String> arrayMap = new ArrayListsMap<>();
        Map<String, String> arrayMapNew = new ArrayListsMap<>();
        arrayMap.put("one", "one");
        System.out.println(arrayMap.put("one", "not one"));
        arrayMap.put("two", "two");
        System.out.println(arrayMap.keySet());
        System.out.println(arrayMap.values());
        System.out.println(arrayMap.get("two"));
        System.out.println(arrayMap);
        System.out.println(arrayMap.remove("one"));
        System.out.println(arrayMap);
        arrayMapNew.put("sec", "sec");
        arrayMapNew.put("first", "first");
        System.out.println(arrayMapNew);
        arrayMap.putAll(arrayMapNew);
        System.out.println(arrayMap);
        arrayMapNew.clear();
        System.out.println(arrayMapNew);



    }
}
