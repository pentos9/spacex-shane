package com.buzz.test.pattern.behavioral.observer.generic;

import com.buzz.test.pattern.behavioral.observer.WeatherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GWeather extends Observable<GWeather, Race, WeatherType> {
    private final Logger logger = LoggerFactory.getLogger(GWeather.class);
    private WeatherType currentWeather;

    public GWeather() {
        this.currentWeather = WeatherType.SUNNY;
    }

    public void timePass() {
        WeatherType[] weatherTypes = WeatherType.values();
        currentWeather = weatherTypes[(currentWeather.ordinal() + 1) % weatherTypes.length];
        logger.info("The weather changed to {}.", currentWeather);
        notifyObservers(currentWeather);
    }
}
