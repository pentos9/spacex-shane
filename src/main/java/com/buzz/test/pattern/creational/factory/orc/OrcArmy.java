package com.buzz.test.pattern.creational.factory.orc;

import com.buzz.test.pattern.creational.factory.Army;

public class OrcArmy implements Army {

    private static final String DESCRIPTION = "this is the Orc Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
