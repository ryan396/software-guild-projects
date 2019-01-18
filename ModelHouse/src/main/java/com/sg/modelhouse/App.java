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
public class App {
    public static void main(String[] args) {
        
        double[] coordinates = {25.4, 33.52};
        String[] materials = {"wood", "metal", "furnace", "toilet"};
        
        
        HouseGPS house1 = new HouseGPS(); 
        house1.setCityName("Minneapolis");
        house1.setSquareFootage(1500.45);
        house1.setCoordinates(coordinates);
        
        House3D house2 = new House3D();
        house2.setNumberOfRooms(3);
        house2.setCost(250000.99);
        house2.setMaterials(materials);
        
       
        System.out.println(house1.getCityName());
        
        System.out.println("$" + house2.getCost());
        
    }
}
