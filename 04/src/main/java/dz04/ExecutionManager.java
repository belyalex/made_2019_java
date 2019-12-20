package dz04;

public interface ExecutionManager {
    Context execute(Runnable... tasks);
}
