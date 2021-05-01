package com.elevenzon.sqlite.model;

public class shift1 {

    String date;
    long perso1=-1;
    long perso2=-1;
    long perso3=-1;
    long perso4=-1;
    long perso5=-1;
    long perso6=-1;
    long perso7=-1;
    long perso8=-1;

    public shift1() {
    }

    public shift1(String date, long perso1, long perso2, long perso3, long perso4, long perso5, long perso6, long perso7, long perso8) {
        this.date = date;
        this.perso1 = perso1;
        this.perso2 = perso2;
        this.perso3 = perso3;
        this.perso4 = perso4;
        this.perso5 = perso5;
        this.perso6 = perso6;
        this.perso7 = perso7;
        this.perso8 = perso8;
    }

    public String getID() {
        return date;
    }

    public void setID(String ID) {
        this.date = ID;
    }

    public long getPerso1() {
        return perso1;
    }

    public void setPerso1(long perso1) {
        this.perso1 = perso1;
    }

    public long getPerso2() {
        return perso2;
    }

    public void setPerso2(long perso2) {
        this.perso2 = perso2;
    }

    public long getPerso3() {
        return perso3;
    }

    public void setPerso3(long perso3) {
        this.perso3 = perso3;
    }

    public long getPerso4() {
        return perso4;
    }

    public void setPerso4(long perso4) {
        this.perso4 = perso4;
    }

    public long getPerso5() {
        return perso5;
    }

    public void setPerso5(long perso5) {
        this.perso5 = perso5;
    }

    public long getPerso6() {
        return perso6;
    }

    public void setPerso6(long perso6) {
        this.perso6 = perso6;
    }

    public long getPerso7() {
        return perso7;
    }

    public void setPerso7(long perso7) {
        this.perso7 = perso7;
    }

    public long getPerso8() {
        return perso8;
    }

    public void setPerso8(long perso8) {
        this.perso8 = perso8;
    }
}
