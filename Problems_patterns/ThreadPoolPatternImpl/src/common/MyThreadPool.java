package common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final List<Thread> workingThreads;

    public MyThreadPool(int threads) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workingThreads = new ArrayList<>(threads);

        for (int i = 0; i < threads; i++){
            Thread newthread = new Thread(new Worker());
            this.workingThreads.add(newthread);
            newthread.start();
        }
    }
    public class Worker implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("In thread");
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        }
        catch (InterruptedException e){
            System.out.println("Cannot put??");
        }

    }

    public void shutdown() {
        for (Thread worker : workingThreads) {
            worker.interrupt();
        }
    }
}
