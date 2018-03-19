package cn.lddsdu.failed;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jack on 18/3/17.
 *
 * 暂没有考虑线程安全
 */

public class multi_thread {
    public static void main(String[] args){
        final int task_num = 0 ;
        BlockingQueue<Task> taskqueue = new ArrayBlockingQueue<Task>(1000);
        ThreadPool threadPool = new ThreadPool(3);
        for (int i = 0 ; i < 1000 ; i ++){
            Task task = new Task(new Runnable() {
                public void run() {
                    System.out.println("task" + " start");
                    try {
                        Thread.sleep(10);//java睡眠时间单位为毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("task" + " end");
                }
            });
            taskqueue.add(task);
        }

            threadPool.exec(taskqueue);

    }
}
