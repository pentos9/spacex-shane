package com.buzz.test.pattern.structural.facade;

public class FacedeApp {

    public static void main(String[] args) {
        DwarvenGoldmineFacade facade = new DwarvenGoldmineFacade();
        facade.startNewDay();
        facade.digOutGold();
        facade.endOfDay();
    }
}
