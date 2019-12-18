package dz04;

import org.junit.Test;

import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestExecutionManager {
    @Test
    public void Test() {
        ExecutionManager executionManager = new ExecutionManagerImpl(Executors.newCachedThreadPool());

        Context context = executionManager.execute(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + i);
                        try {
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        context.onFinish(() -> {
            System.out.println("All tasks done.");
        });


        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(context.isFinished());

        assertTrue((5000<=context.getStatistics().getMinExecutionTimeInMs())
                && (6000>context.getStatistics().getMinExecutionTimeInMs()));
        assertTrue((5000<=context.getStatistics().getMaxExecutionTimeInMs())
                && (6000>context.getStatistics().getMaxExecutionTimeInMs()));
        assertTrue((5000<=context.getStatistics().getAverageExecutionTimeInMs())
                && (6000>context.getStatistics().getAverageExecutionTimeInMs()));

        assertEquals(1, context.getCompletedTaskCount());
        assertEquals(0, context.getFailedTaskCount());
        assertEquals(0, context.getInterruptedTaskCount());

        printInfo(context);

        try {
            sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.interrupt();
        context.awaitTermination();

        assertTrue(context.isFinished());

        assertTrue((5000<=context.getStatistics().getMinExecutionTimeInMs())
                && (6000>context.getStatistics().getMinExecutionTimeInMs()));
        assertTrue((10000<=context.getStatistics().getMaxExecutionTimeInMs())
                && (11000>context.getStatistics().getMaxExecutionTimeInMs()));
        assertTrue((7500<=context.getStatistics().getAverageExecutionTimeInMs())
                && (8000>context.getStatistics().getAverageExecutionTimeInMs()));

        assertEquals(2, context.getCompletedTaskCount());
        assertEquals(0, context.getFailedTaskCount());
        assertEquals(0, context.getInterruptedTaskCount());

        printInfo(context);

    }

    private void printInfo(Context context) {
        System.out.println("Finished: " + context.isFinished());

        System.out.println("Min exec time: " + context.getStatistics().getMinExecutionTimeInMs());
        System.out.println("Max exec time: " + context.getStatistics().getMaxExecutionTimeInMs());
        System.out.println("Avg exec time: " + context.getStatistics().getAverageExecutionTimeInMs());

        System.out.println("Completed: " + context.getCompletedTaskCount());
        System.out.println("Failed: " + context.getFailedTaskCount());
        System.out.println("Interrupted: " + context.getInterruptedTaskCount());
    }
}
