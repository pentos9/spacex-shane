package com.buzz.test.pattern.factory;

public interface KingdomFactory {
    Castle createCastle();

    King createKing();

    Army createArmy();
}
