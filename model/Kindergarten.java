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
public class Kindergarten extends Place {

    public Kindergarten(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Kindergarten() {
    }

    @Override
    public String toString() {
        return "Kindergarten: " + super.toString();
    }
}
