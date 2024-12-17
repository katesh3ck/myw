package com.example.demo;

public class Performance {
    private int id;
    private int employeeId;
    private double performanceScore;

    public Performance(int id, int employeeId, double performanceScore) {
        this.id = id;
        this.employeeId = employeeId;
        this.performanceScore = performanceScore;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    @Override
    public String toString() {
        return "Performance{id=" + id + ", employeeId=" + employeeId + ", performanceScore=" + performanceScore + "}";
    }
}