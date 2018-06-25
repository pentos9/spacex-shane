package com.buzz.test.pattern.structural.adaptor.formatter;


public class NewLineFormatter implements TextFormattable {

    @Override
    public String formatText(String text) {
        String formatterText = text.replace(".", "\n");
        return formatterText;
    }
}
