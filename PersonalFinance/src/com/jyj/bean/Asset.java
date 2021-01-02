package com.jyj.bean;

public class Asset {
    private Integer A_id;
    private String email;
    private double card;
    private double weChat;
    private double alipay;
    private double other;

    public Asset() {
    }

    public Asset(Integer a_id, String email, double card, double weChat, double alipay, double other) {
        A_id = a_id;
        this.email = email;
        this.card = card;
        this.weChat = weChat;
        this.alipay = alipay;
        this.other = other;
    }

    public Integer getA_id() {
        return A_id;
    }

    public void setA_id(Integer a_id) {
        A_id = a_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCard() {
        return card;
    }

    public void setCard(double card) {
        this.card = card;
    }

    public double getWeChat() {
        return weChat;
    }

    public void setWeChat(double weChat) {
        this.weChat = weChat;
    }

    public double getAlipay() {
        return alipay;
    }

    public void setAlipay(double alipay) {
        this.alipay = alipay;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }


    @Override
    public String toString() {
        return "Asset{" +
                "A_id=" + A_id +
                ", email='" + email + '\'' +
                ", card=" + card +
                ", weChat=" + weChat +
                ", alipay=" + alipay +
                ", other=" + other +
                '}';
    }
}
