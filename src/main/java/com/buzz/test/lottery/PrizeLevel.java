package com.buzz.test.lottery;

public enum PrizeLevel {
    SPECIAL(0, "特等奖", 1), FIRST(1, "一等奖", 2), SECOND(2, "二等奖", 3), THIRD(3, "三等奖", 5);


    private long id;
    private String name;
    private long numberOfWinner;

    private PrizeLevel(long id, String name, long numberOfWinner) {
        this.id = id;
        this.name = name;
        this.numberOfWinner = numberOfWinner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumberOfWinner() {
        return numberOfWinner;
    }

    public void setNumberOfWinner(long numberOfWinner) {
        this.numberOfWinner = numberOfWinner;
    }
}
