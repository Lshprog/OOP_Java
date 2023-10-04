import common.MyThreadPool;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        MyThreadPool threadPool = new MyThreadPool(3);

        for (int i = 0; i < 10; i++) {
            int taskId = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName());
            });
        }

        //threadPool.shutdown();

    }
}