/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author François
 */
public class Images {
    
    private String id;
    
    private List<String> imagesNamesList;
    
    public Images(){
        
    }
    
    public Images(String id, List<String> imagesNamesList){
        this.id = id;
        this.imagesNamesList = imagesNamesList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    
}
