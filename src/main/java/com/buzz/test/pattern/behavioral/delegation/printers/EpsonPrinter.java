package com.buzz.test.pattern.behavioral.delegation.printers;

import com.buzz.test.pattern.behavioral.delegation.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EpsonPrinter implements Printer {
    private Logger logger = LoggerFactory.getLogger(EpsonPrinter.class);

    @Override
    public void print(String message) {
        logger.info("Epson Printer:{}", message);

    }
}
