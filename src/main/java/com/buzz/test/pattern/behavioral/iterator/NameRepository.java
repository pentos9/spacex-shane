package com.buzz.test.pattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class NameRepository<T> implements Container {

    private List<T> names = new ArrayList<>();

    public void add(T... args) {
        for (T ele : args) {
            names.add(ele);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new NameIterator<>();
    }


    private class NameIterator<S> implements Iterator<S> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            if (index < names.size()) {
                return true;
            }

            return false;
        }

        @Override
        public S next() {

            if (this.hasNext()) {
                return (S) names.get(index++);
            }

            return null;
        }
    }
}
