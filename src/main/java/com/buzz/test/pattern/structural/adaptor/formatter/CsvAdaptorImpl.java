package com.buzz.test.pattern.structural.adaptor.formatter;

public class CsvAdaptorImpl implements TextFormattable {

    private CsvFormattable csvFormattable;

    public CsvAdaptorImpl(CsvFormattable csvFormattable) {
        this.csvFormattable = csvFormattable;
    }

    @Override
    public String formatText(String text) {
        String formattedText = csvFormattable.formatCsvText(text);
        return formattedText;
    }
}
