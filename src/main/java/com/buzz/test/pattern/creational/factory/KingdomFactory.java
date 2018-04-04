package com.buzz.test.pattern.creational.factory;

public interface KingdomFactory {
    Castle createCastle();

    King createKing();

    Army createArmy();
}
