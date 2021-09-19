package mc.playground.weird;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class Tmp {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();  // HashMap  ConcurrentHashMap
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(3, 3);
        map.keySet();
    }
}
