package mc.playground.map;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

import static mc.McThread.safeSleep;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicHashMapThreadSafeTest {
    private static String REPORT;

    @Test
    void diff_guestThenHost() {
        Map<Integer, Integer> map = new HashMap<>();

        new Thread(() -> {
            safeSleep(500);
            map.put(2, nextInt());
        }).start();

        assertThrows(ConcurrentModificationException.class, () -> {
            map.compute(1, (key, oldValue) -> {
                safeSleep(1000);
                return nextInt();
            });
        });
    }

    @Test
    void same_guestThenHost() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, nextInt());
        REPORT = "";

        new Thread(() -> {
            safeSleep(500);
            map.put(1, nextInt());
            REPORT += "Guest";
        }).start();

        map.compute(1, (key, oldValue) -> {
            safeSleep(1000);
            REPORT += "Host";
            return nextInt();
        });

        safeSleep(100);
        assertEquals("GuestHost", REPORT);
    }
}
