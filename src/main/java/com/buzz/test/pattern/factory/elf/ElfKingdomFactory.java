package com.buzz.test.pattern.factory.elf;

import com.buzz.test.pattern.factory.Army;
import com.buzz.test.pattern.factory.Castle;
import com.buzz.test.pattern.factory.King;
import com.buzz.test.pattern.factory.KingdomFactory;
import com.buzz.test.pattern.factory.elf.ElfArmy;
import com.buzz.test.pattern.factory.elf.ElfCastle;
import com.buzz.test.pattern.factory.elf.ElfKing;

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
