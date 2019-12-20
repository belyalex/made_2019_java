package dz04;

public class TimeredRunnable implements Runnable {
    private final Runnable runnable;

    private Long execTime = null;

    private volatile boolean done = false;
    private boolean completed = false;
    private boolean failed = false;

    public boolean isDone() {
        return done;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isFailed() {
        return failed;
    }

    TimeredRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            runnable.run();
            execTime = System.currentTimeMillis() - start;
            completed = true;
        } catch (Exception e) {
            failed = true;
        } finally {
            done = true;
        }
    }

    Long getExecTime() {
        return execTime;
    }
}
