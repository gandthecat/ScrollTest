/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrolltest.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Win 7
 */
public class Rockford extends Entity {
    
    public enum action {
        stationary, 
        rightwalk,
        leftwalk,
        upwalk,
        downwalk
    }
    public action actionNow;
    public String[] spriteFiles = {
        "rockfordsp/rf1.png","rockfordsp/rf2.png","rockfordsp/lw1.png","rockfordsp/lw2.png","rockfordsp/lw3.png",
        "rockfordsp/rw1.png","rockfordsp/rw2.png","rockfordsp/rw3.png",
        "rockfordsp/dw1.png","rockfordsp/dw2.png","rockfordsp/dw3.png","rockfordsp/dw4.png", 
        "rockfordsp/uw1.png","rockfordsp/uw2.png","rockfordsp/uw3.png","rockfordsp/uw4.png"
    };
    public int x;
    public int y;
    
    public Rockford(String name, ArrayList<Image> spriteImages){
        super(name, spriteImages);
        actionNow = action.stationary;
        loadRockford();
    }
    
    public void loadRockford(){
        
          BufferedImage src = null; 
          for(String f : spriteFiles){
            try {
                src = ImageIO.read(getClass().getResource(f));
            } catch (IOException ex) {
                Logger.getLogger(Rockford.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            spriteImages.add(src);
          
          }
       
    }
    
}
