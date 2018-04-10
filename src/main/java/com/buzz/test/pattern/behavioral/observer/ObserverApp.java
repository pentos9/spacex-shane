package com.buzz.test.pattern.behavioral.observer;

import com.buzz.common.print.PrintUtil;
import com.buzz.test.pattern.behavioral.observer.generic.GHobbits;
import com.buzz.test.pattern.behavioral.observer.generic.GOrcs;
import com.buzz.test.pattern.behavioral.observer.generic.GWeather;

public class ObserverApp {
    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.addObserver(new Orcs());
        weather.addObserver(new Hobbits());

        weather.timePass();
        weather.timePass();
        weather.timePass();
        weather.timePass();

        PrintUtil.print("--- Running Generic Version ---");
        GWeather gWeather = new GWeather();
        gWeather.addObserver(new GOrcs());
        gWeather.addObserver(new GHobbits());

        gWeather.timePass();
        gWeather.timePass();
        gWeather.timePass();
        gWeather.timePass();
    }
}
