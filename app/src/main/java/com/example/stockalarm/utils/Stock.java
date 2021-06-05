package com.example.stockalarm.utils;

public class Stock {
    private String 名稱;
    private String 時間;
    private String 成交;
    private String 買進;
    private String 賣出;
    private String 漲跌;
    private String 張數;
    private String 昨收;
    private String 開盤;
    private String 最高;
    private String 最低;

    public Stock(String 名稱) {
        this.名稱 = 名稱;
    }

    public String get名稱() {
        return 名稱;
    }

    public void set名稱(String 名稱) {
        this.名稱 = 名稱;
    }

    public String get時間() {
        return 時間;
    }

    public void set時間(String 時間) {
        this.時間 = 時間;
    }

    public String get成交() {
        return 成交;
    }

    public void set成交(String 成交) {
        this.成交 = 成交;
    }

    public String get買進() {
        return 買進;
    }

    public void set買進(String 買進) {
        this.買進 = 買進;
    }

    public String get賣出() {
        return 賣出;
    }

    public void set賣出(String 賣出) {
        this.賣出 = 賣出;
    }

    public String get漲跌() {
        return 漲跌;
    }

    public void set漲跌(String 漲跌) {
        this.漲跌 = 漲跌;
    }

    public String get張數() {
        return 張數;
    }

    public void set張數(String 張數) {
        this.張數 = 張數;
    }

    public String get昨收() {
        return 昨收;
    }

    public void set昨收(String 昨收) {
        this.昨收 = 昨收;
    }

    public String get開盤() {
        return 開盤;
    }

    public void set開盤(String 開盤) {
        this.開盤 = 開盤;
    }

    public String get最高() {
        return 最高;
    }

    public void set最高(String 最高) {
        this.最高 = 最高;
    }

    public String get最低() {
        return 最低;
    }

    public void set最低(String 最低) {
        this.最低 = 最低;
    }

    @Override
    public String toString() {
        return  "名稱=" + 名稱 + '，' +
                "時間=" + 時間 + '，' +
                "成交=" + 成交 + '，' +
                "買進=" + 買進 + '，' +
                "賣出=" + 賣出 + '，' +
                "漲跌=" + 漲跌 + '，' +
                "張數=" + 張數 + '，' +
                "昨收=" + 昨收 + '，' +
                "開盤=" + 開盤 + '，' +
                "最高=" + 最高 + '，' +
                "最低=" + 最低;
    }
}
