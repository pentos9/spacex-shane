package com.buzz.test.pattern.factory.orc;

import com.buzz.test.pattern.factory.Army;

public class OrcArmy implements Army {

    private static final String DESCRIPTION = "this is the Orc Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
