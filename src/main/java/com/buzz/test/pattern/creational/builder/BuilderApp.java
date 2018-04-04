package com.buzz.test.pattern.creational.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderApp {

    private static Logger logger = LoggerFactory.getLogger(Hero.Builder.class);

    public static void main(String[] args) {
        Hero mage = new Hero.Builder(Profession.MAGE, "mage").withArmor(Armor.CHAIN_MAIL).withHairColor(HairColor.BLACK).build();

        logger.info(String.format("mage:%s", mage));

        Hero warrior = new Hero.Builder(Profession.WARRIOR, "Amberjill").withArmor(Armor.PLATE_MAIL).withHairColor(HairColor.BROWN).withHairType(HairType.CURLY).withWeapon(Weapon.SWORD).build();
        logger.info(String.format("warrior:%s", warrior));

        Hero thief = new Hero.Builder(Profession.THIEF, "Desmond").withArmor(Armor.LEATHERS).withHairColor(HairColor.BLOND).withHairType(HairType.LONG_CURLY).withWeapon(Weapon.DAGGER).build();
        logger.info(String.format("thief:%s", thief));

        Hero priest = new Hero.Builder(Profession.PRIEST, "priest").withArmor(Armor.CLOTHES).withHairColor(HairColor.RED).withHairType(HairType.SHORT).withWeapon(Weapon.DAGGER).build();
        logger.info(String.format("priest:%s", priest));

    }
}
