package com.buzz.test.pattern.structural.adaptor.formatter;

public class CsvFormatter implements CsvFormattable {
    @Override
    public String formatCsvText(String text) {
        String formattedText = text.replace(".", ",");
        return formattedText;
    }
}
