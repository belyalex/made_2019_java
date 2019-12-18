package dz04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ExecutorService executorService;

    public ExecutionManagerImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Context execute(Runnable... tasks) {
        List<TimeredRunnable> timeredTasks = new ArrayList<>(tasks.length);
        List<Future<?>> futures = new ArrayList<>(tasks.length);

        for (Runnable task : tasks) {
            TimeredRunnable t = new TimeredRunnable(task);
            timeredTasks.add(t);
            futures.add(executorService.submit(t));
        }

        return new ContextImpl(timeredTasks, futures);
    }
}
