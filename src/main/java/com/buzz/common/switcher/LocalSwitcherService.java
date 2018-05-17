package com.buzz.common.switcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LocalSwitcherService implements SwitcherService {

    private static ConcurrentMap<String, Switcher> switchers = new ConcurrentHashMap<>();

    private Map<String, List<SwitcherListener>> listenerMap = new ConcurrentHashMap<>();

    public static Switcher getSwitcherStatic(String name) {
        return switchers.get(name);
    }

    @Override
    public Switcher getSwitcher(String name) {
        return switchers.get(name);
    }

    @Override
    public List<Switcher> getAllSwitchers() {
        return new ArrayList<>(switchers.values());
    }

    public static void putSwitcher(Switcher switcher) {
        if (switcher == null) {
            throw new RuntimeException("LocalSwitcherService addSwitcher Error: switcher is null");
        }
        switchers.put(switcher.getName(), switcher);
    }

    @Override
    public void initSwitcher(String name, boolean initValue) {
        setValue(name, initValue);
    }

    @Override
    public boolean isOpen(String name) {
        Switcher switcher = switchers.get(name);
        return switcher != null && switcher.isOn();
    }

    @Override
    public boolean isOpen(String name, boolean defaultValue) {
        Switcher switcher = switchers.get(name);
        if (switcher == null) {
            switchers.putIfAbsent(name, new Switcher(name, defaultValue));
            switcher = switchers.get(name);
        }
        return switcher.isOn();
    }

    @Override
    public void setValue(String name, boolean value) {
        putSwitcher(new Switcher(name, value));

        List<SwitcherListener> listeners = listenerMap.get(name);
        if (listeners != null) {
            for (SwitcherListener listener : listeners) {
                listener.onValueChanged(name, value);
            }
        }

    }

    @Override
    public void registerListener(String switchName, SwitcherListener listener) {
        synchronized (listenerMap) {
            if (listenerMap.get(switchName) == null) {
                List<SwitcherListener> listeners = Collections.synchronizedList(new ArrayList<SwitcherListener>());
                listenerMap.put(switchName, listeners);
                listeners.add(listener);
            } else {
                List<SwitcherListener> listeners = listenerMap.get(switchName);
                if (!listeners.contains(listener)) {
                    listeners.add(listener);
                }
            }
        }
    }

    @Override
    public void unRegisterListener(String switcherName, SwitcherListener listener) {
        synchronized (listenerMap) {
            if (listener == null) {
                listenerMap.remove(switcherName);
            } else {
                List<SwitcherListener> listeners = listenerMap.get(switcherName);
                listeners.remove(listener);
            }
        }
    }
}
