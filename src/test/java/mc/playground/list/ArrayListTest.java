package mc.playground.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    @Test
    void testRemoval() {
        List<String> a = new ArrayList<>();
        a.add("sb");
        a.add("sb2");
        a.add("sb3");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : a) {
                a.remove("sb");
                System.out.println(s);
            }
        });
    }

    @Test
    void testAdd() {
        List<String> a = new ArrayList<>();
        a.add("sb");
        a.add("sb2");
        a.add("sb3");
        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : a) {
                a.add(System.currentTimeMillis() + "sb");
                System.out.println(s);
            }
        });
    }
}
