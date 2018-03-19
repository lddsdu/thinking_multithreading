package cn.lddsdu.failed;

/**
 * Created by jack on 18/3/17.
 */

public class SmartThread extends Thread{
    private ThreadPool threadPool;
    private Task task;
    private static int num = 0;

    public SmartThread(ThreadPool threadPool) {
        System.out.println("create thread"+(++num));
        this.threadPool = threadPool;
    }

    public void setTask(Task task){
        this.task = task;
    }

    @Override
    public void run() {
        task.run();
        //在task执行完成之后，将自己还回到threadpool中去
        System.out.println("返回到threadpool中");
        threadPool.backThread(this);
    }
}
