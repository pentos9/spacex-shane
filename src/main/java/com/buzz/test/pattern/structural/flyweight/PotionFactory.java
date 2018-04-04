package com.buzz.test.pattern.structural.flyweight;

import org.apache.taglibs.standard.lang.jstl.EnumeratedMap;

import java.util.EnumMap;
import java.util.Map;


public class PotionFactory {
    private final Map<PotionType, Potion> potions;

    public PotionFactory() {
        this.potions = new EnumMap(PotionType.class);
    }

    Potion createPotion(PotionType type) {
        Potion potion = potions.get(type);

        if (potion == null) {
            switch (type) {
                case HEALING:
                    potion = new HealingPotion();
                    potions.put(type, potion);
                    break;

                case INVISIBILITY:
                    potion = new InvisiblePotion();
                    potions.put(type, potion);
                    break;

                case STRENGTH:
                    potion = new StrengthPotion();
                    potions.put(type, potion);
                    break;

                case HOLY_WATER:
                    potion = new HolyWaterPotion();
                    potions.put(type, potion);
                    break;

                case POISON:
                    potion = new PoisonPotion();
                    potions.put(type, potion);
                    break;

                default:
                    throw new IllegalArgumentException("Potion type is unsupported");
            }
        }


        return potion;
    }

    static enum PotionType {
        HEALING, INVISIBILITY, STRENGTH, HOLY_WATER, POISON
    }
}
