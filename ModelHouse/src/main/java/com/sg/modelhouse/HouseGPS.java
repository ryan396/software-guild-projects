/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.modelhouse;

/**
 *
 * @author rianu
 */
public class HouseGPS {
    private String cityName;
    private double squareFootage;
    private double[] coordinates = new double[2];
    private String street;
    private String color;
    
    
    public HouseGPS() {
        
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

   

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
}
