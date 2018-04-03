package com.buzz.test.dynamic;


public class Client {
    public static void main(String[] args) {
        MovieService movieService = new JDKDynamicProxy(new MovieServiceImpl()).getInstance();
        movieService.release();
    }
}
