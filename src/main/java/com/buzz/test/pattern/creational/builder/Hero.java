package com.buzz.test.pattern.creational.builder;

public class Hero {
    private Profession profession;

    private String name;
    private HairType hairType;
    private HairColor hairColor;

    private Armor armor;
    private Weapon weapon;

    public Hero(Builder builder) {
        this.profession = builder.profession;
        this.name = builder.name;
        this.hairType = builder.hairType;
        this.hairColor = builder.hairColor;
        this.armor = builder.armor;
        this.weapon = builder.weapon;
    }

    public Profession getProfession() {
        return profession;
    }

    public String getName() {
        return name;
    }

    public HairType getHairType() {
        return hairType;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public Armor getArmor() {
        return armor;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "profession=" + profession +
                ", name='" + name + '\'' +
                ", hairType=" + hairType +
                ", hairColor=" + hairColor +
                ", armor=" + armor +
                ", weapon=" + weapon +
                '}';
    }

    static class Builder {
        private Profession profession;

        private String name;
        private HairType hairType;
        private HairColor hairColor;

        private Armor armor;
        private Weapon weapon;

        public Builder(Profession profession, String name) {
            if (profession == null || name == null) {
                throw new IllegalArgumentException("profession and name cannot be nulls.s");
            }
            this.profession = profession;
            this.name = name;
        }

        public Builder withHairType(HairType hairType) {
            this.hairType = hairType;
            return this;
        }

        public Builder withHairColor(HairColor hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder withArmor(Armor armor) {
            this.armor = armor;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.weapon = weapon;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }
    }
}
