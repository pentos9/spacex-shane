package com.buzz.test.pattern.behavioral.iterator;

public class IteratorApp {
    public static void main(String[] args) {
        NameRepository<String> nameRepository = new NameRepository<>();
        nameRepository.add("Barcelona", "Madrid", "Liverpool");
        for (Iterator iterator = nameRepository.iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            System.out.println(name);
        }
    }
}
