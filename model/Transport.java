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



public class Transport extends Place {
    

    public Transport(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Transport() {
    }
    
    @Override
    public String toString() {
        return "Transport: " + super.toString();
    }
    
    
    
}
