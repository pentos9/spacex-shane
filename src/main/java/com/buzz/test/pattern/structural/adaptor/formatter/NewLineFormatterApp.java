package com.buzz.test.pattern.structural.adaptor.formatter;

public class NewLineFormatterApp {
    public static void main(String[] args) {
        String testString = "Formatting line 1.Formatting line 2.Formatting line 3.";
        TextFormattable textFormattable = new NewLineFormatter();
        String resultString = textFormattable.formatText(testString);
        System.out.println(resultString);

        CsvFormattable csvFormattable = new CsvFormatter();
        TextFormattable csvAdaptor = new CsvAdaptorImpl(csvFormattable);
        String csvResultString = csvAdaptor.formatText(testString);
        System.out.println(csvResultString);
    }
}
