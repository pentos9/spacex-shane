package com.buzz.test.pattern.other.polymorphism;

import com.buzz.test.pattern.other.polymorphism.instance.Centipede;
import com.buzz.test.pattern.other.polymorphism.instance.Chicken;
import com.buzz.test.pattern.other.polymorphism.instance.Crab;
import com.buzz.test.pattern.other.polymorphism.instance.Dog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PolymorphismTest {

    private static Logger logger = LoggerFactory.getLogger(PolymorphismTest.class);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        logger.info(String.valueOf(getLegNumber(new Dog())));
        logger.info(String.valueOf(getLegNumber(new Chicken())));
        logger.info(String.valueOf(getLegNumber(new Crab())));
        logger.info(String.valueOf(getLegNumber(new Centipede())));
    }

    public static int getLegNumber(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("animal can not be null");
        }
        return animal.legs();
    }
}
