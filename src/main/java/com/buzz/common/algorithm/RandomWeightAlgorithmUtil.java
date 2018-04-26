package com.buzz.common.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class RandomWeightAlgorithmUtil {

    static private class WeightUnit {
        private Long id;
        private Long weight;

        public WeightUnit(Long id, Long weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    private static Logger logger = LoggerFactory.getLogger(RandomWeightAlgorithmUtil.class);

    public static Long getRandomTarget(Map<Long, Long> weightUnitIdMap) {

        if (weightUnitIdMap == null || weightUnitIdMap.size() == 0) {
            throw new IllegalArgumentException("weightUnitIdMap can not be null or empty");
        }

        List<WeightUnit> weights = weightUnitIdMap.entrySet().stream().map(weightUnit -> new WeightUnit(weightUnit.getKey(), weightUnit.getValue())).collect(Collectors.toList());
        Long totalWright = weights.stream().mapToLong(weightUnit -> weightUnit.weight).sum();

        if (totalWright <= 0) {
            throw new IllegalArgumentException(String.format("totalWright can not be greater than 0,weightUnitIdMap=%s", weightUnitIdMap));
        }

        Integer randomInt = new Random().nextInt(totalWright.intValue());
        Long random = randomInt.longValue();

        for (WeightUnit weightUnit : weights) {
            random = random - weightUnit.weight;
            if (random < 0) {
                return weightUnit.id;
            }
        }

        return null;
    }


    public static void main(String[] args) {
        Map<Long, Long> weightUnitIdMap = new HashMap<>();

        List<WeightUnit> atoms = new ArrayList<>();

        final int maxId = 10000;
        for (int i = 0; i < 3; i++) {
            Integer id = new Random().nextInt(maxId);
            Integer weight = new Random().nextInt(10) + 1;
            WeightUnit atom = new WeightUnit(id.longValue(), weight.longValue());
            atoms.add(atom);
        }

        atoms.forEach(atom -> {
            weightUnitIdMap.put(atom.id, atom.weight);
        });

        logger.info(String.format("weightUnitIdMap=%s", weightUnitIdMap));

        Map<Long, Long> resultMap = new HashMap<>();
        final int loopTimes = 10000;
        for (int i = 0; i < loopTimes; i++) {
            Long target = getRandomTarget(weightUnitIdMap);
            Long count = resultMap.get(target);
            if (count == null) {
                count = 0L;
                resultMap.put(target, count);
            } else {
                resultMap.put(target, ++count);
            }
        }

        final Map<Long, Double> percentageMap = new HashMap<>();
        Long totalWeight = resultMap.values().stream().mapToLong(item -> item).sum();
        resultMap.entrySet().stream().forEach(entry -> percentageMap.put(entry.getKey(), entry.getValue().doubleValue() / totalWeight));
        logger.info(String.format("resultMap=%s", resultMap));
        logger.info(String.format("percentageMap=%s", percentageMap));
    }

}
