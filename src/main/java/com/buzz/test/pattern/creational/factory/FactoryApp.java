package com.buzz.test.pattern.creational.factory;


import com.buzz.test.pattern.creational.factory.elf.ElfKingdomFactory;
import com.buzz.test.pattern.creational.factory.orc.OrcKingdomFactory;

public class FactoryApp {

    private King king;
    private Castle castle;
    private Army army;

    public static void main(String[] args) {

        FactoryApp app = new FactoryApp();

        app.createKingdom(FactoryMaker.makeFactory(FactoryMaker.KingdomType.ELF));

        System.out.println(app.getCastle().getDescription());
        System.out.println(app.getKing().getDescription());
        System.out.println(app.getArmy().getDescription());


        app.createKingdom(FactoryMaker.makeFactory(FactoryMaker.KingdomType.ORC));

        System.out.println(app.getCastle().getDescription());
        System.out.println(app.getKing().getDescription());
        System.out.println(app.getArmy().getDescription());


    }

    public static class FactoryMaker {

        public enum KingdomType {
            ELF, ORC
        }

        public static KingdomFactory makeFactory(KingdomType type) {
            switch (type) {
                case ELF:
                    return new ElfKingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("kingdom type not support!");
            }
        }

    }

    public void createKingdom(final KingdomFactory kingdomFactory) {
        setKing(kingdomFactory.createKing());
        setCastle(kingdomFactory.createCastle());
        setArmy(kingdomFactory.createArmy());
    }

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
