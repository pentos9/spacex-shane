package com.buzz.test.core.lambda;

@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
}
