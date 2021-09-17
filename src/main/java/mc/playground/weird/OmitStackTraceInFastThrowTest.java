package mc.playground.weird;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class OmitStackTraceInFastThrowTest {

    /**
     * Please RUN without debug
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 1; i < 300 * 1000; i++) {
            try {
                Integer xx = 1;
                if (1 > 0) {
                    xx = null;
                }
                System.out.println(xx.toString());

            } catch (Exception e) {
                String trace = ExceptionUtils.getStackTrace(e);

                if (trace.contains(OmitStackTraceInFastThrowTest.class.getSimpleName())) {
                    System.err.print("* ");
                } else {
                    System.out.println("\nJVM has decided to use pre-allocated exception after tries: " + i);
                    System.exit(0);
                }
            }
        }

        System.err.println("\nNo pre-allocated exception used after 300.000 tries");
    }
}
