package com.buzz.test;


import com.buzz.test.ifaces.BlockChain;

public class BlockChainTestApp {
    public static void main(String[] args) {
        BlockChain blockChain = new BlockChain() {
            @Override
            public void addBlock() {
                System.out.println("add block");
            }
        };

        blockChain.printBlockChain();
        blockChain.addBlock();
        BlockChain.addTransaction();
    }
}
