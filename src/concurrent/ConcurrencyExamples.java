package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ssiddiqu on 4/11/18.
 */
public class ConcurrencyExamples {
    public static void threadTest() {
        Runnable task = () -> {
            try {
                String tName= Thread.currentThread().getName();
                System.out.println("Current thread is " + tName);
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Thread "+tName+" just woke up from sleep");
            }
            catch(InterruptedException ine) {
                ine.printStackTrace();
            }
        };
        Thread t1 = new Thread(task);
        task.run();

        t1.start();

        Thread t2 = new Thread(task);
        t2.start();
        System.out.println("Done");

    }
    public static void callableTest() {
        Executor excutor = Executors.newFixedThreadPool(1);
    }
    public static void executorTest() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Current thread is " + threadName);
            try {
                TimeUnit.SECONDS.sleep(10);
            }
            catch(InterruptedException ie) {
                System.err.println("Someone just interrupted");
            }
            System.out.println("Done with thread "+threadName);
        });
        System.out.println("After first executor in main");
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Current thread is " + threadName);
            System.out.println("Done with thread "+threadName);
        });
        System.out.println("After second executor in main");
        shutDown(executor);
    }
    private static void shutDown(ExecutorService executor) {

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
    public static void main(String[] args) {
//        threadTest();
        executorTest();
    }
}
