/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Pierre
 */
public class Adress extends Place {

    public Adress(Walk walk, Drive drive) {
        super(walk, drive);
    }

    public Adress() {
    }

    @Override
    public String toString() {
        return "Adress: " + super.toString();
    }
}
