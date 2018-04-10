package com.buzz.test.pattern.behavioral.delegation.printers;

import com.buzz.test.pattern.behavioral.delegation.Printer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CanonPrinter implements Printer {
    private Logger logger = LoggerFactory.getLogger(CanonPrinter.class);

    @Override
    public void print(String message) {
        logger.info("Canon Printer: {}s", message);
    }
}
