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
public class Triangle {
    
    double base;
    double side1;
    double side2;
    double height;
    double area;
    double perimeter;
    
    public double getPerimeter() {
        perimeter = base + side1 + side2;
        return perimeter;
    }
    
    public double getArea() {
        area = (1/2) * base * height;
        return area;
    }
}
