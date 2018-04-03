package com.buzz.test.pattern.factory.elf;

import com.buzz.test.pattern.factory.Castle;

public class ElfCastle implements Castle {

    private static final String DESCRIPTION = "this is Elf castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
