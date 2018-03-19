package cn.lddsdu;

/**
 * Created by jack on 18/3/18.
 */
public class SmartThread extends Thread{
    private ThreadPool threadPool;

    public SmartThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while(true){
            //从taskqueue中拿任务task  1000ms 没有取到数据，则停止
            Runnable task  = threadPool.gettask();
            if(task != null){
                task.run();
            }else{
                if(threadPool.currentmaxcore()){
                    threadPool.subcurrenpoolsize();
                    break;
                };
            }
        }
    }
}
