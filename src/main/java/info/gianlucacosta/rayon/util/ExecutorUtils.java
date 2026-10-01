package info.gianlucacosta.rayon.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


public interface ExecutorUtils {
    /**
     * Prevents the executor service from receiving further jobs and waits for its termination
     *
     * @param executorService The executor service to stop
     */
    public static void stopAndJoin(ExecutorService executorService) {
        executorService.shutdown();

        while (true) {
            try {
                if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS)) {
                    break;
                }
            } catch (InterruptedException e) {
                //Just do nothing
            }
        }
    }
}
