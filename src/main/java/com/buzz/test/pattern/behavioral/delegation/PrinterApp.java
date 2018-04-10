package com.buzz.test.pattern.behavioral.delegation;

import com.buzz.test.pattern.behavioral.delegation.printers.CanonPrinter;
import com.buzz.test.pattern.behavioral.delegation.printers.EpsonPrinter;
import com.buzz.test.pattern.behavioral.delegation.printers.HPPrinter;

public class PrinterApp {

    private static final String message = "stay hungry,stay foolish";

    public static void main(String[] args) {
        PrinterController hpPrinter = new PrinterController(new HPPrinter());
        hpPrinter.print(message);

        PrinterController canonPrinter = new PrinterController(new CanonPrinter());
        canonPrinter.print(message);

        PrinterController epsonPrinter = new PrinterController(new EpsonPrinter());
        epsonPrinter.print(message);
    }
}
