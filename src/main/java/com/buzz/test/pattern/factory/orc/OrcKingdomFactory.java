package com.buzz.test.pattern.factory.orc;


import com.buzz.test.pattern.factory.Army;
import com.buzz.test.pattern.factory.Castle;
import com.buzz.test.pattern.factory.King;
import com.buzz.test.pattern.factory.KingdomFactory;
import com.buzz.test.pattern.factory.orc.OrcArmy;
import com.buzz.test.pattern.factory.orc.OrcCastle;
import com.buzz.test.pattern.factory.orc.OrcKing;

public class OrcKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new OrcCastle();
    }

    @Override
    public King createKing() {
        return new OrcKing();
    }

    @Override
    public Army createArmy() {
        return new OrcArmy();
    }
}
