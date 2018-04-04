package com.buzz.test.pattern.creational.factory.orc;

import com.buzz.test.pattern.creational.factory.Castle;

public class OrcCastle implements Castle {

    private static final String DESCRIPTION = "this is the Orc castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
