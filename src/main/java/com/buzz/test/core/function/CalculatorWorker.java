package com.buzz.test.core.function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatorWorker {

    private static Logger logger = LoggerFactory.getLogger(CalculatorWorker.class);

    public static void main(String[] args) {
        doCalculate();
    }

    public static void doSum() {
        Thread worker = new Thread(() -> {
            ICalculator adder = new ICalculator() {
                @Override
                public long calculate(long x, long y) {
                    return x + y;
                }
            };

            for (long i = 0; i < 10; i++) {
                adder.calculate(i, i);
                logger.info(String.valueOf(adder.calculate(i, i)));
            }
        });

        worker.start();
    }


    public static void calculate(long x, long y, ICalculator calculator) {
        logger.info(String.valueOf(calculator.calculate(x, y)));
    }

    public static void doCalculate() {
        calculate(100, 200, ((x, y) -> x + y));
        calculate(100, 20, (x, y) -> x - y);
        calculate(10, 20, (x, y) -> x * y);
        calculate(15, 5, (x, y) -> x / y);
    }
}

