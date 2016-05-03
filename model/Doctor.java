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
public class Doctor extends Place {

    public Doctor(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return "Doctor: " + super.toString();
    }
}
