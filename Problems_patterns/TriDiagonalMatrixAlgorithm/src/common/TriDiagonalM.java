package common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TriDiagonalM {

    private List<List<Double>> coefs;

    private List<Double> resultList;

    private int len;

    public TriDiagonalM(List<List<Double>> coefs, int n) {
        this.coefs = coefs;
        this.len = n;
        resultList = populateZeros(this.len);

    }

    private List<Double> populateZeros(int n){
        List<Double> newlist = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            newlist.add(0.0);
        }
        return newlist;
    }

    public List<Double> findSolution(){
        List<Double> f_arr = populateZeros(this.len);
        List<Double> u = populateZeros(this.len);
        List<Double> v = populateZeros(this.len);
        double coef = 0.0;

        f_arr.set(1,-coefs.get(0).get(1));
        f_arr.set(this.len-1, -coefs.get(2).get(this.len-1));

        u.set(1,0.0);
        v.set(1,1.0);

        ExecutorService executor = new ThreadPoolExecutor(3, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (int inner_index = 2; inner_index < this.len; inner_index++) {
            coef = this.coefs.get(0).get(inner_index) / this.coefs.get(1).get(inner_index - 1);
            Future<Double> dfuture = executor.submit(new Task(this.coefs.get(3).get(inner_index), coef, this.coefs.get(3).get(inner_index - 1)));
            Future<Double> ffuture = executor.submit(new Task(f_arr.get(inner_index), coef, f_arr.get(inner_index - 1)));

            //Future<Double> bfuture = executor.submit(new Task(coefs.get(1).get(inner_index),coef,coefs.get(2).get(inner_index)));
            //System.out.println("problem");
            this.coefs.get(1).set(inner_index, this.coefs.get(1).get(inner_index) - coef * this.coefs.get(2).get(inner_index - 1));

            // Maybe not super effective implementation below ...

            while (true) {
                //System.out.println("done?");
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

        u.set(this.len - 1, coefs.get(3).get(this.len - 1)/coefs.get(1).get(this.len - 1));
        v.set(this.len - 1, f_arr.get(this.len - 1)/coefs.get(1).get(this.len - 1));

        ((ThreadPoolExecutor) executor).setCorePoolSize(2);

        for(int inner_index = this.len - 2; inner_index > 0; inner_index--){
            Future<Double> ufuture = executor.submit(new Task(this.coefs.get(3).get(inner_index),
                    this.coefs.get(2).get(inner_index),
                    u.get(inner_index+1),
                    this.coefs.get(1).get(inner_index)));
            Future<Double> vfuture = executor.submit(new Task(f_arr.get(inner_index),
                    this.coefs.get(2).get(inner_index),
                    v.get(inner_index+1),
                    this.coefs.get(1).get(inner_index)));


            while (true) {
                if (ufuture.isDone() && vfuture.isDone()) {
                    break;
                }
            }

            try {
                u.set(inner_index, ufuture.get());
                v.set(inner_index, vfuture.get());
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }


        }

        this.resultList.set(0,
                ( this.coefs.get(3).get(0) - this.coefs.get(0).get(0) * u.get(this.len - 1) - this.coefs.get(2).get(0)*u.get(1))
                /
                (this.coefs.get(1).get(0) + this.coefs.get(0).get(0) * v.get(this.len - 1) + this.coefs.get(2).get(0)*v.get(1))
        );

        ((ThreadPoolExecutor) executor).setCorePoolSize(4);

        for(int i = 1; i < this.len; i++){
            int finalI = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    resultList.set(finalI, u.get(finalI) + resultList.get(0)*v.get(finalI));
                }
            });
        }


        return resultList;
    }


}
