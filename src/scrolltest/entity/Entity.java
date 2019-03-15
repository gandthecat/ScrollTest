/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrolltest.entity;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Win 7
 */
public class Entity {
    
    public String name;
    public ArrayList<Image> spriteImages = new ArrayList<Image>();
    
    public Entity(String name, ArrayList<Image> spriteImages){
        this.name = name; 
        this.spriteImages = spriteImages;
    }
    
}
