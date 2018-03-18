package com.buzz.test.ifaces;


public interface BlockChain {

    void addBlock();

    default void printBlockChain() {
        System.out.println("printing block chain...");
    }

    static void addTransaction() {
        System.out.println("add transaction...");
    }
}
