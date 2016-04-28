/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author François
 */
public class Supermarket extends Place {

    public Supermarket(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Supermarket() {
    }
    
    @Override
    public String toString() {
        return "Supermarket: " + super.toString();
    }
    
    
    
}
