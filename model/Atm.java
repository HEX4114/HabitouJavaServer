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
public class Atm extends Place {

    public Atm(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Atm() {
    }

    @Override
    public String toString() {
        return "Atm: " + super.toString();
    }
}
