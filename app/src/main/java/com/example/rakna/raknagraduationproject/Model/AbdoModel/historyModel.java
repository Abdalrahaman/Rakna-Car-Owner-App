package com.example.rakna.raknagraduationproject.Model.AbdoModel;

public class historyModel {

    private String name, number, date, from_time, to_time, location, imgurl;
    private double rate;

    public historyModel(String name, String number, String date, String from_time, String to_time, String location, String imgurl, double rate) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.from_time = from_time;
        this.to_time = to_time;
        this.location = location;
        this.imgurl = imgurl;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
