package mc.playground.map;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashMapNullKeyTest {

    @Test
    public void testNullKeyHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("concac", "cailon");
        map.put(null, "cailon-1");
        map.put(null, "cailon-2");

        assertEquals(2, map.size());
    }

    @Test()
    public void testNullKeyConcurrentHashMap() {
        assertThrows(NullPointerException.class, () -> {
            Map<String, String> map = new ConcurrentHashMap<>();
            map.put(null, "cailon-1");
        });
    }
}