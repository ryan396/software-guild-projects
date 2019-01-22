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
public class Square extends Shape {
    
    double area;
    double perimeter;
    double side;
    
    @Override
    public double getPerimeter() {
        perimeter = side*4;
        return perimeter;
    }
    
    @Override
    public double getArea() {
        area = side * side;
        return area;
    }
    
}
