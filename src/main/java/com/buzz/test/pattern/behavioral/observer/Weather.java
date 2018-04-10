package com.buzz.test.pattern.behavioral.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Weather {
    private Logger logger = LoggerFactory.getLogger(Weather.class);

    private WeatherType currentWeather;

    private List<WeatherObserver> observes;

    public Weather() {
        observes = new ArrayList<>();
        currentWeather = WeatherType.SUNNY;
    }

    public void addObserver(WeatherObserver observer) {
        if (observer == null) {
            return;
        }
        observes.add(observer);
    }

    public void removeObserver(WeatherObserver observer) {
        if (observer == null) {
            return;
        }
        observes.remove(observer);
    }

    public void timePass() {
        WeatherType[] weatherTypes = WeatherType.values();
        currentWeather = weatherTypes[(currentWeather.ordinal() + 1) % weatherTypes.length];
        logger.info("the current weather is :{}.", currentWeather);
        notifyObservers();
    }

    public void notifyObservers() {
        for (WeatherObserver observer : observes) {
            observer.update(currentWeather);
        }
    }
}
