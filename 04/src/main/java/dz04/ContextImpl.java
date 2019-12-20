package dz04;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.LongStream;

import static java.lang.Math.round;

public class ContextImpl implements Context {
    private final List<TimeredRunnable> timeredTasks;
    private final List<Future<?>> futures;

    ContextImpl(List<TimeredRunnable> timeredTasks, List<Future<?>> futures) {
        this.timeredTasks = timeredTasks;
        this.futures = futures;
    }


    @Override
    public int getCompletedTaskCount() {
        return getTaskCountWhere(TimeredRunnable::isCompleted);
    }

    @Override
    public int getFailedTaskCount() {
        return getTaskCountWhere(TimeredRunnable::isFailed);
    }

    @Override
    public int getInterruptedTaskCount() {
        return (int) futures.stream()
                .filter(Future::isCancelled)
                .count();
    }

    @Override
    public void interrupt() {
        futures.forEach(future -> future.cancel(false));
    }

    @Override
    public boolean isFinished() {
        return getTaskCountWhere(x -> true) == timeredTasks.size();
    }

    @Override
    public void onFinish(Runnable callback) {
        Thread thread = new Thread(() -> {
            awaitTermination();
            callback.run();
        });
        thread.start();
    }

    @Override
    public void awaitTermination() {
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ExecutionStatistics getStatistics() {
        return new ExecutionStatisticsImpl();
    }

    public class ExecutionStatisticsImpl implements ExecutionStatistics {
        @Override
        public int getMinExecutionTimeInMs() {
            return (int) getTimeredRunnableStreamTimes().min().orElse(-1);
        }

        @Override
        public int getMaxExecutionTimeInMs() {
            return (int) getTimeredRunnableStreamTimes().max().orElse(-1);
        }

        @Override
        public int getAverageExecutionTimeInMs() {
            return (int) round(getTimeredRunnableStreamTimes().average().orElse(-1));
        }

        private LongStream getTimeredRunnableStreamTimes() {
            return timeredTasks.stream()
                    .filter(TimeredRunnable::isDone)
                    .mapToLong(TimeredRunnable::getExecTime)
                    .filter(Objects::nonNull);
        }
    }

    private int getTaskCountWhere(Predicate<? super TimeredRunnable> predicate) {
        return (int) timeredTasks.stream()
                .filter(TimeredRunnable::isDone)
                .filter(predicate)
                .count();
    }
}
