package common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Task implements Callable<Double> {

    private Double value;

    private Double coef;

    private Double prev_value;


    public Task(Double value, Double coef, Double prev_value) {
        this.value = value;
        this.coef = coef;
        this.prev_value = prev_value;
    }


    @Override
    public Double call() throws Exception {
        return value - coef*prev_value;
    }
}
