package com.buzz.test.pattern.behavioral.delegation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrinterController {
    private Logger logger = LoggerFactory.getLogger(PrinterController.class);

    private Printer printer;

    public PrinterController(Printer printer) {
        this.printer = printer;
    }

    public void print(final String message) {
        if (message != null) {
            printer.print(message);
        }
    }
}
