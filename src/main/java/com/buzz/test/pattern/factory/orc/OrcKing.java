package com.buzz.test.pattern.factory.orc;


import com.buzz.test.pattern.factory.King;

public class OrcKing implements King {

    private static final String DESCRIPTION = "this is the Orc King!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
