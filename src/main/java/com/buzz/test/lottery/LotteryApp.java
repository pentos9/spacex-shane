package com.buzz.test.lottery;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class LotteryApp {

    private static Logger logger = LoggerFactory.getLogger(LotteryApp.class);

    private static final int TOTAL_PARTICIPANT_NUMBER = 100;

    private static List<PrizeLevel> prizeLevels = new ArrayList<>();
    private static Collection<Participant> participants = new ArrayList<>();


    public static void main(String[] args) {

        init();

        listPrizeLevels();

        prizeLevels.forEach(prizeLevel -> {
            Collection<Participant> winners = lottery(prizeLevel, participants, prizeLevel.getNumberOfWinner());
            listWinner(prizeLevel, winners);
            participants = CollectionUtils.removeAll(participants, winners);
        });

    }

    public static void init() {

        //init level
        prizeLevels = Arrays.asList(PrizeLevel.values());

        //init participant
        for (int id = 1; id <= TOTAL_PARTICIPANT_NUMBER; id++) {
            Participant participant = new Participant(id, id, "P" + id);
            join(participant);
        }

    }

    public static void listPrizeLevels() {
        System.out.println("\t奖品池");
        prizeLevels.forEach(prizeLevel -> {
            System.out.print(prizeLevel.getName() + "\t");
        });
        System.out.println("\n");
    }

    public static void listWinner(PrizeLevel prizeLevel, Collection<Participant> luckyWinners) {
        System.out.println(prizeLevel.getName());
        luckyWinners.forEach(winner -> {
            System.out.print(winner.getName() + "\t");
        });
        System.out.println();
    }

    public static void join(Participant participant) {
        if (participant == null) {
            return;
        }
        participants.add(participant);
    }

    public static List<Participant> lottery(PrizeLevel prizeLevel, Collection<Participant> participants, long numberOfWinner) {
        Assert.notNull(prizeLevel, "prize level should not be null");
        Assert.notNull(participants, "participants should not be null");
        Assert.isTrue(participants.size() >= numberOfWinner, String.format("参与抽奖人数不能少得奖人数,size=[%s],numberOfWinner=[%s]", participants.size(), numberOfWinner));

        List<Participant> allParticipant = new ArrayList<>();
        List<Participant> luckyWinners = new ArrayList<>();
        allParticipant.addAll(participants);

        for (int times = 0; times < numberOfWinner; times++) {
            int luckyIndex = getLuckyParticipantIndex(allParticipant);
            luckyWinners.add(allParticipant.get(luckyIndex));
            allParticipant.remove(luckyIndex);

        }

        return luckyWinners;
    }

    private static int getLuckyParticipantIndex(Collection<Participant> participants) {
        int length = participants.size();
        return RandomUtils.nextInt(0, length);
    }
}
