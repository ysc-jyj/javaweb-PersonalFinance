package com.jyj.bean;


public class Payment {
    private Integer ID;
    private String email;
    private String payments;//收/支
    private String type;
    private String way;
    private double money;
    private String pdate;
    private String remarks;

    public Payment() {
    }

    public Payment(Integer id) {
        this.ID=id;
    }

    public Payment(Integer ID, String email, String payments, String type, String way, double money, String pdate, String remarks) {
        this.ID = ID;
        this.email = email;
        this.payments = payments;
        this.type = type;
        this.way = way;
        this.money = money;
        this.pdate = pdate;
        this.remarks = remarks;
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayments() {
        return payments;
    }

    public void setPayments(String payments) {
        this.payments = payments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", payments='" + payments + '\'' +
                ", type='" + type + '\'' +
                ", way='" + way + '\'' +
                ", money=" + money +
                ", pdate='" + pdate + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
