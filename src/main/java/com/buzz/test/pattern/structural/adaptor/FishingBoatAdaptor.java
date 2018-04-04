package com.buzz.test.pattern.structural.adaptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FishingBoatAdaptor implements RowingBoat {

    private static Logger logger = LoggerFactory.getLogger(FishingBoatAdaptor.class);

    private FishingBoat fishingBoat;

    public FishingBoatAdaptor() {
        fishingBoat = new FishingBoat();
    }

    @Override
    public void row() {
        fishingBoat.sail();
    }
}
