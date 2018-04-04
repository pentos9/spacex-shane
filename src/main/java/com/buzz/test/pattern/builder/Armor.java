package com.buzz.test.pattern.builder;

public enum Armor {
    CLOTHES("clothes"), LEATHERS("leathers"), CHAIN_MAIL("chain_mail"), PLATE_MAIL("plate_mail");
    private String name;

    private Armor(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                '}';
    }
}
