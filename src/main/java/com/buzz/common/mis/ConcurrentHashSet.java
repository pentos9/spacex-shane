package com.buzz.common.mis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashSet<E> extends AbstractSet<E> implements Set<E>, Serializable {

    private static Logger logger = LoggerFactory.getLogger(ConcurrentHashSet.class);

    private static Object PRESENT = new Object();

    private final ConcurrentMap<E, Object> concurrentMap;

    public ConcurrentHashSet() {
        this.concurrentMap = new ConcurrentHashMap<>();
    }

    public ConcurrentHashSet(int initialCapacity) {
        this.concurrentMap = new ConcurrentHashMap<E, Object>(initialCapacity);
    }

    @Override
    public Iterator<E> iterator() {
        return concurrentMap.keySet().iterator();
    }

    @Override
    public int size() {
        return concurrentMap.size();
    }

    @Override
    public boolean isEmpty() {
        return concurrentMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return concurrentMap.containsKey(o);
    }

    @Override
    public boolean add(E e) {
        return concurrentMap.put(e, PRESENT) == null;
    }

    @Override
    public void clear() {
        concurrentMap.clear();
    }

    @Override
    public boolean remove(Object o) {
        return concurrentMap.remove(o) == PRESENT;
    }
}
