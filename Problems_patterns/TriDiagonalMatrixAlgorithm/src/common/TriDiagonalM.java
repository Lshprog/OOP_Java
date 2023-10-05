package common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TriDiagonalM {

    private final List<List<Double>> coefs;

    private List<Double> resultList = new ArrayList<>();

    private final int len;

    public TriDiagonalM(List<List<Double>> coefs, int n) {
        this.coefs = coefs;
        this.len = n;
    }

    public List<Double> findSolution(){
        List<Double> f_arr = new ArrayList<>(this.len + 1);

        ExecutorService executor = new ThreadPoolExecutor(3, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (int inner_index = 3; inner_index < this.len + 1; inner_index++) {
            double coef = this.coefs.get(0).get(inner_index) / this.coefs.get(1).get(inner_index - 1);
            Future<Double> dfuture = executor.submit(new Task(this.coefs.get(3).get(inner_index), coef, this.coefs.get(3).get(inner_index - 1)));
            Future<Double> ffuture = executor.submit(new Task(f_arr.get(inner_index), coef, f_arr.get(inner_index - 1)));

            //Future<Double> bfuture = executor.submit(new Task(coefs.get(1).get(inner_index),coef,coefs.get(2).get(inner_index)));

            this.coefs.get(1).set(inner_index, this.coefs.get(1).get(inner_index) - coef * this.coefs.get(2).get(inner_index - 1));

            // Maybe not super effective implementation below ...

            while (true) {
                if (dfuture.isDone() && ffuture.isDone()) {
                    break;
                }
            }

            try {
                this.coefs.get(3).set(inner_index, dfuture.get());
                f_arr.set(inner_index, ffuture.get());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }




        return null;
    }


}
