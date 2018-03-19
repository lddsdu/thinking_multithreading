package cn.lddsdu.failed;

/**
 * Created by jack on 18/3/17.
 */
public class Task implements Runnable {
    private Runnable runnable;

    public Task(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setRunnable(Runnable runnable){
        this.runnable = runnable;
    }

    public void run() {
        runnable.run();
    }
}
