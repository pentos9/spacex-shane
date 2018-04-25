package com.buzz.test.pattern.behavioral.filter;

public class ContactFilter extends AbstractFilter {
    @Override
    public String executor(Order order) {
        String result = super.executor(order);

        if (order.getContactNumber() == null
                || order.getContactNumber().isEmpty()
                || order.getContactNumber().matches(".*[^\\d]+.*")
                || order.getContactNumber().length() != 11) {
            return result + "invalid contact number";
        } else {
            return result;
        }
    }
}
