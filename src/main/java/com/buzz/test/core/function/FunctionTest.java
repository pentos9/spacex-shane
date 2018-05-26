package com.buzz.test.core.function;

import com.buzz.model.user.User;
import com.buzz.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.*;

public class FunctionTest {

    private static Logger logger = LoggerFactory.getLogger(FunctionTest.class);

    public static void main(String[] args) {
        run();
        predicate();
        function();
        supplier();
        unary();
        binary();
    }

    public static void run() {
        Consumer<Integer> consumer = (x) -> {
            logger.info(String.valueOf(x));
        };

        consumer.accept(12);
    }

    public static void predicate() {
        Predicate<String> predicate = (x) -> x.startsWith("ui");
        logger.info(String.valueOf(predicate.test("ui-interface")));
    }

    public static void function() {
        Function<String, String> toUpperCase = String::toUpperCase;
        logger.info(toUpperCase.apply("data"));
    }

    public static void supplier() {
        Supplier<User> supplier = User::new;
        logger.info(JsonUtil.toJson(supplier.get()));
    }

    public static void unary() {
        UnaryOperator<String> unary = String::toString;
        logger.info(JsonUtil.toJson(unary.apply("UNARY")));
    }

    public static void binary() {
        BinaryOperator<String> binary = (x, y) -> x + y;
        logger.info(JsonUtil.toJson(binary.apply("First", "Second")));
    }

    /**
     Predicate -- 传入一个参数，返回一个bool结果， 方法为boolean test(T t)
     Consumer -- 传入一个参数，无返回值，纯消费。 方法为void accept(T t)
     Function -- 传入一个参数，返回一个结果，方法为R apply(T t)
     Supplier -- 无参数传入，返回一个结果，方法为T get()
     UnaryOperator -- 一元操作符， 继承Function,传入参数的类型和返回类型相同。
     BinaryOperator -- 二元操作符， 传入的两个参数的类型和返回类型相同， 继承BiFunction
     */
}
