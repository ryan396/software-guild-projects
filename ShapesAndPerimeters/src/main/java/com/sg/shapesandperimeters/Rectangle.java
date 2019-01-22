/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.shapesandperimeters;

/**
 *
 * @author rianu
 */
public class Rectangle {
    
    double sideX;
    double sideY;
    double area;
    double perimeter;
    
    public double getPerimeter() {
        perimeter = sideX*2 + sideY*2;
        return perimeter;
    }
    
    public double getArea(){
        area = sideX * sideY;
        return area;
    }
}
