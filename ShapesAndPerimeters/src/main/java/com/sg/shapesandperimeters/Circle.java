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
public class Circle {
    private static final double PI = 3.14;
    double radius;
    double perimeter;
    double area;
    
    public double getPerimeter() {
        perimeter = 2 * PI * radius;
        return perimeter;
    }
    
    public double getArea() {
        area = PI * (radius * radius);
        return area;
    }
    
}
