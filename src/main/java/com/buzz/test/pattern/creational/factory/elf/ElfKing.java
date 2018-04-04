package com.buzz.test.pattern.creational.factory.elf;

import com.buzz.test.pattern.creational.factory.King;

public class ElfKing implements King {

    private static final String DESCRIPTION = "This is Elf king!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
