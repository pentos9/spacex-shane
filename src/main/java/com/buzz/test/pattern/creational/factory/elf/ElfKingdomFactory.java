package com.buzz.test.pattern.creational.factory.elf;

import com.buzz.test.pattern.creational.factory.Army;
import com.buzz.test.pattern.creational.factory.Castle;
import com.buzz.test.pattern.creational.factory.King;
import com.buzz.test.pattern.creational.factory.KingdomFactory;

public class ElfKingdomFactory implements KingdomFactory {

    @Override
    public Castle createCastle() {
        return new ElfCastle();
    }

    @Override
    public King createKing() {
        return new ElfKing();
    }

    @Override
    public Army createArmy() {
        return new ElfArmy();
    }
}
