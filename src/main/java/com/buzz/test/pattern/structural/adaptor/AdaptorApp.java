package com.buzz.test.pattern.structural.adaptor;

public class AdaptorApp {
    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdaptor());
        captain.row();
    }
}
