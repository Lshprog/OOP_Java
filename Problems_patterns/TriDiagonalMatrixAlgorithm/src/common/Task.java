package common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Task implements Callable<Double> {

    private Double value;

    private Double coef;

    private Double prev_value;

    private Double divisor = 0.0;


    public Task(Double value, Double coef, Double prev_value) {
        this.value = value;
        this.coef = coef;
        this.prev_value = prev_value;
    }
    public Task(Double value, Double coef, Double prev_value, Double divisor) {
        this.value = value;
        this.coef = coef;
        this.prev_value = prev_value;
        this.divisor = divisor;
    }


    @Override
    public Double call() throws Exception {
        if(divisor == 0.0){
            return value - coef*prev_value;
        }
        return (value - coef*prev_value)/divisor;
    }
}
