package com.buzz.test.dynamic;


public class MovieServiceImpl implements MovieService {
    @Override
    public void release() {
        System.out.println("A new movie is released");
    }
}
