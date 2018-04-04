package com.buzz.test.pattern.structural.flyweight;

import com.buzz.common.print.PrintUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlchemistShop {

    private Logger logger = LoggerFactory.getLogger(AlchemistShop.class);

    private List<Potion> topShelf;
    private List<Potion> bottomShelf;

    public AlchemistShop() {
        this.topShelf = new ArrayList<>();
        this.bottomShelf = new ArrayList<>();
        fillShelves();
    }

    private void fillShelves() {

        PotionFactory potionFactory = new PotionFactory();
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.HEALING));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.HEALING));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.INVISIBILITY));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.INVISIBILITY));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.STRENGTH));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.HEALING));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.INVISIBILITY));
        topShelf.add(potionFactory.createPotion(PotionFactory.PotionType.STRENGTH));

        bottomShelf.add(potionFactory.createPotion(PotionFactory.PotionType.HOLY_WATER));
        bottomShelf.add(potionFactory.createPotion(PotionFactory.PotionType.HOLY_WATER));
        bottomShelf.add(potionFactory.createPotion(PotionFactory.PotionType.POISON));
        bottomShelf.add(potionFactory.createPotion(PotionFactory.PotionType.POISON));
    }

    public final List<Potion> getTopShelf() {
        return Collections.unmodifiableList(this.topShelf);
    }

    public final List<Potion> getBottomShelf() {
        return Collections.unmodifiableList(this.bottomShelf);
    }

    public void enumerate() {
        logger.info("enumerate top shelf potions");

        for (Potion potion : topShelf) {
            potion.drink();
        }

        logger.info("enumerate bottom shelf potions");
        for (Potion potion : bottomShelf) {
            potion.drink();
        }
    }
}
