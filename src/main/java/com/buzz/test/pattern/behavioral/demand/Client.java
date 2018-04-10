package com.buzz.test.pattern.behavioral.demand;

public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command concreteCommand = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker(concreteCommand);
        invoker.action();
    }
}
