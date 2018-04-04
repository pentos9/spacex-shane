package com.buzz.test.pattern.structural.facade;

import java.util.ArrayList;
import java.util.List;

public class DwarvenGoldmineFacade {
    private final List<DwarvenMineWorker> workers;

    public DwarvenGoldmineFacade() {
        this.workers = new ArrayList<>();
        workers.add(new DwarvenTunnelDigger());
        workers.add(new DwarvenGoldDigger());
        workers.add(new DwarvenCartOperator());
    }

    private void makeAction(List<DwarvenMineWorker> workers, DwarvenMineWorker.Action... actions) {
        for (DwarvenMineWorker worker : workers) {
            worker.action(actions);
        }
    }

    public void startNewDay() {
        makeAction(workers, DwarvenMineWorker.Action.WAKE_UP, DwarvenMineWorker.Action.GO_TO_MINE);
    }

    public void digOutGold() {
        makeAction(workers, DwarvenMineWorker.Action.WORK);
    }

    public void endOfDay() {
        makeAction(workers, DwarvenMineWorker.Action.GO_HOME, DwarvenMineWorker.Action.SLEEP);
    }
}
