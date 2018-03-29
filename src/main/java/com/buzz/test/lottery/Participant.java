package com.buzz.test.lottery;

public class Participant {
    private long userId;
    private long employeeId;
    private String name;

    public Participant(long userId, long employeeId, String name) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "userId=" + userId +
                ", employeeId=" + employeeId +
                ", name='" + name + '\'' +
                '}';
    }
}
