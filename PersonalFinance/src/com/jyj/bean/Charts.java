package com.jyj.bean;

import java.util.List;

public class Charts<T> {
    private String type;
    private double typeTotalMoney;
    private double totalPay;
    private double totalIncome;
    private double balance;
    private String timeT;
    private List<T> list;
    public Charts() {
    }

    public Charts(String type, double typeTotalMoney) {
        this.type = type;
        this.typeTotalMoney = typeTotalMoney;
    }

    public Charts(double totalPay, double totalIncome, double balance, String timeT) {
        this.totalPay = totalPay;
        this.totalIncome = totalIncome;
        this.balance = balance;
        this.timeT = timeT;
    }

    public Charts(List<T> list) {
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTypeTotalMoney() {
        return typeTotalMoney;
    }

    public void setTypeTotalMoney(double typeTotalMoney) {
        this.typeTotalMoney = typeTotalMoney;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getbalance() {
        return balance;
    }

    public void setbalance(double balance) {
        this.balance = balance;
    }

    public String getTimeT() {
        return timeT;
    }

    public void setTimeT(String timeT) {
        this.timeT = timeT;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String toStringP() {
        return "Charts{" +
                "type='" + type + '\'' +
                ", typeTotalMoney='" + typeTotalMoney + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Charts{" +
                "totalPay=" + totalPay +
                ", totalIncome=" + totalIncome +
                ", balance=" + balance +
                ", timeT='" + timeT + '\'' +
                ", list=" + list +
                '}';
    }

}
