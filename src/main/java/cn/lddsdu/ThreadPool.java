package cn.lddsdu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by jack on 18/3/18.
 *
 * 暂不考虑线程安全的问题
 */
public class ThreadPool {
    private int corepoolsize;
    private int maxiumpoolsize;
    private int currentpoolsize;
    private BlockingQueue<Runnable> taskQueue;
    private int maxTask = 100;
    private fullHandler handler;

    private static Logger logger = Logger.getLogger(String.valueOf(ThreadPool.class.getClass()));

    public void subcurrenpoolsize(){
        currentpoolsize --;
    }

    public boolean currentmaxcore(){
        return currentpoolsize > corepoolsize;
    }

    public Runnable gettask(){
        Runnable runnable;
        try {
            runnable = taskQueue.poll(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            return null;
        }
        return runnable;
    }

    public ThreadPool(int corepoolsize, int maxiumpoolsize, BlockingQueue<Runnable> taskQueue,fullHandler handler) {
        currentpoolsize = 0;
        this.corepoolsize = corepoolsize;
        this.maxiumpoolsize = maxiumpoolsize;
        this.taskQueue = taskQueue;
        this.handler = handler;
    }


    public void execute(Runnable runnable)  {
        /*    ***添加处理的逻辑***
        if(基本线程池已经满了){
            if(工作队列已经满了){
                if(整个线程池已经满了){
                    交给饱和策略;
                }else{
                    创建新的工作线程;
                }
            }else{
                任务存进工作队列;
            }
        }else{
            创建新的线程;
        }
         */
        if(currentpoolsize >= corepoolsize){
            if(taskQueue.size() >= maxTask){
                if(currentpoolsize >= maxiumpoolsize){
                    //开始handle这个情况
                    this.handler.handle();
                }else{
                    currentpoolsize ++;
                    new SmartThread(this).start();
                }
            }else{
                try {
                    taskQueue.put(runnable);
                } catch (InterruptedException e) {
                    throw new RuntimeException("taskqueue add error because of interrupt");
                }
            }
        }else{
            currentpoolsize ++;
            new SmartThread(this).start();
        }


    }
}
