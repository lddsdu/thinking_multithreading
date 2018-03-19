package cn.lddsdu.failed;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by jack on 18/3/17.
 */
public class ThreadPool {
    private int size;
    private BlockingQueue<SmartThread> SmartThreads;

    public ThreadPool(int size) {
        this.size = size;
        SmartThreads = new ArrayBlockingQueue<SmartThread>(size);
        for (int i = 0 ; i < size ; i++){
            SmartThread sm = new SmartThread(this);
            SmartThreads.add(sm);
        }
    }

    public void backThread(SmartThread sm){
        SmartThreads.add(sm);
    }

    public void exec(BlockingQueue<Task> taskqueue)  {
        while(!taskqueue.isEmpty()){

            SmartThread sm = null;
            try {
                sm = SmartThreads.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Task task = taskqueue.poll();

            sm.start();//一次严重的翻车事故,没有了解线程的和线程对象的实质。start()重复调用显然会出现错误

            /*
            思考：关于线程的调用问题
            线程对象 thread ，为了便于开创新的线程，java中使用对象的形式来描述，使用调用其start方法来启动线程
            注意：在启动子线程之后祝线程便不再可以直接操作对象来指挥这个子线程来做什么事情了，其变得"不可控",其
            抛出的错误也没有办法在主线程的代码中来catch到。
             */

        }
    }
}
