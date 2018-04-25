package com.buzz.test.pattern.behavioral.filter;

public class FilterApp {
    public static void main(String[] args) {
        FilterManager filterManager = new FilterManager();
        filterManager.addFilter(new NameFilter());
        filterManager.addFilter(new ContactFilter());
        filterManager.addFilter(new AddressFilter());
        filterManager.addFilter(new DepositFilter());
        filterManager.addFilter(new OrderFilter());

        Client client = new Client();
        client.setFilterManager(filterManager);

    }
}
