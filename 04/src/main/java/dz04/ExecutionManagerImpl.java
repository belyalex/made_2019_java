package dz04;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionManagerImpl implements ExecutionManager {
    private final ExecutorService executorService;

    public ExecutionManagerImpl(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Context execute(Runnable... tasks) {
        List<TimeredRunnable> timeredTasks = Stream.of(tasks)
                .map(TimeredRunnable::new)
                .collect(Collectors.toList());
        List<Future<?>> futures = timeredTasks.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());

        return new ContextImpl(timeredTasks, futures);
    }
}
