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
public class House3D {
    private int numberOfRooms;
    private String[] materials;
    private double cost;
    private int numberOfBathrooms;
    private String cabinetColor; 

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String[] getMaterials() {
        return materials;
    }

    public void setMaterials(String[] materials) {
        this.materials = materials;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public String getCabinetColor() {
        return cabinetColor;
    }

    public void setCabinetColor(String cabinetColor) {
        this.cabinetColor = cabinetColor;
    }
    
    
}
