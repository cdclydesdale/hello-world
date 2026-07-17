package com.example;

public class Hotel {
    private String hotelName;
    private double hotelPrice;
    private boolean hasPool;

    public Hotel(String hotelName, double hotelPrice, boolean hasPool){
        this.hotelName = hotelName;
        this.hotelPrice = hotelPrice;
        this.hasPool = hasPool;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public double getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(double hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }
}
