package com.buzz.test.pattern.creational.factory.orc;


import com.buzz.test.pattern.creational.factory.Army;
import com.buzz.test.pattern.creational.factory.Castle;
import com.buzz.test.pattern.creational.factory.King;
import com.buzz.test.pattern.creational.factory.KingdomFactory;

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
