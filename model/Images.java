/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author François
 */
public class Images {
       
    private List<String> imagesNamesList;
    
    public Images(){
        imagesNamesList = new ArrayList<>();
    }
    
    public Images(List<String> imagesNamesList){
        this.imagesNamesList = imagesNamesList;
    }

    public List<String> getImagesNamesList() {
        return imagesNamesList;
    }

    public void setImagesNamesList(List<String> imagesNamesList) {
        this.imagesNamesList = imagesNamesList;
    }
    
    public void addImageName(String name)
    {
        imagesNamesList.add(name);
    }

    @Override
    public String toString() {
        return "Images{" + "imagesNamesList=" + imagesNamesList + '}';
    }
    
    

    
    
    
}
