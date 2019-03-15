/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrolltest.level_tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Win 7
 */
public class Boulder extends Tile {
    
    public int boulderDropTrack = 0;
    public int boulderIndex;
    public String[] spriteFiles = {
        "rck/rk1.png","rck/rk2.png","rck/rk3.png","rck/rk4.png"
    };
    
    public Boulder(String name, Image image){
        super(name,image); 
    }
    
    public Boulder(){
        generateBoulder();
    }
    
    public Boulder(int index, int droptrack,String name, int x, int y){
        super(name,x,y);
        this.boulderDropTrack = droptrack;
        this.boulderIndex = index; 
    }
    
    private void generateBoulder(){
        
        BufferedImage src = null; 
          for(String f : spriteFiles){
            try {
                src = ImageIO.read(getClass().getResource(f));
            } catch (IOException ex) {
                Logger.getLogger(Boulder.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            spriteImages.add(src);
          
          }
        
    }
    
}
