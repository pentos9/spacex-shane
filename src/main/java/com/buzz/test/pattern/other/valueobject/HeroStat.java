package com.buzz.test.pattern.other.valueobject;

public class HeroStat {

    private final int strength;
    private final int intelligence;
    private final int luck;

    public HeroStat(int strength, int intelligence, int luck) {
        this.strength = strength;
        this.intelligence = intelligence;
        this.luck = luck;
    }

    public static HeroStat valueOf(int strength, int intelligence, int luck) {
        return new HeroStat(strength, intelligence, luck);
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getLuck() {
        return luck;
    }

    @Override
    public String toString() {
        return "HeroStat{" +
                "strength=" + strength +
                ", intelligence=" + intelligence +
                ", luck=" + luck +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HeroStat heroStat = (HeroStat) o;

        if (strength != heroStat.strength) return false;
        if (intelligence != heroStat.intelligence) return false;
        return luck == heroStat.luck;

    }

    @Override
    public int hashCode() {
        int result = strength;
        result = 31 * result + intelligence;
        result = 31 * result + luck;
        return result;
    }
}
