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

public abstract class Place {
    
    private Walk walk;
    
    private Drive drive;

    public Place() {
    }

    public Place(Walk walk, Drive drive) {
        this.walk = walk;
        this.drive = drive;
    }

    public Walk getWalk() {
        return walk;
    }

    public void setWalk(Walk walk) {
        this.walk = walk;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    @Override
    public String toString() {
        return "Place{" + "walk=" + walk + ", drive=" + drive + '}';
    }
    
    
}
