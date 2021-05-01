package com.elevenzon.sqlite.model;

public class chicha {
    long ID;
    String title;
    double prix;

    String date;
public chicha(){}
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public chicha(long ID, String title, double prix, String date) {
        this.ID = ID;
        this.title = title;
        this.prix = prix;
        this.date = date;
    }
}
