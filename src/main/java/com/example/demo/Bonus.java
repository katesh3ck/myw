package com.example.demo;

import com.example.myw.Employee;

public class Bonus implements Comparable<Bonus> {
    private final Employee employee;
    private final double amount;

    public Bonus(Employee employee, double amount) {
        this.employee = employee;
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Bonus other) {
        return Double.compare(other.amount, this.amount); // Сортировка по убыванию
    }

    @Override
    public String toString() {
        return "Bonus{employee=" + employee + ", amount=" + amount + '}';
    }
}
