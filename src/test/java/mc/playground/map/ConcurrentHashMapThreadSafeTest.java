package mc.playground.map;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static mc.McThread.safeSleep;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrentHashMapThreadSafeTest {
    private static String REPORT;

    @Test
    void diff_guestThenHost() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        REPORT = "";

        new Thread(()->{
            safeSleep(500);
            map.put(2, nextInt());
            REPORT += "Guest";
        }).start();

        map.compute(1, (key, oldValue)->{
            safeSleep(1000);
            REPORT += "Host";
            return nextInt();
        });

        safeSleep(100);
        assertEquals("GuestHost", REPORT);
        assertEquals(2, map.size());
    }

    @Test
    void same_guestThenHost() {
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        REPORT = "";

        new Thread(()->{
            safeSleep(500);
            map.put(1, nextInt());
            REPORT += "Guest";
        }).start();

        map.compute(1, (key, oldValue)->{
            safeSleep(1000);
            REPORT += "Host";
            return nextInt();
        });

        safeSleep(100);
        assertEquals("HostGuest", REPORT);
        assertEquals(1, map.size());
    }
}
