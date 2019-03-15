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
public class Gem extends Tile {
     
    public int gemTimer = 0;
    public String[] spriteFiles = {
        "gem/gem1.png","gem/gem2.png","gem/gem3.png","gem/gem4.png"
    };
    
    public Gem(String name, Image image){
        super(name,image);
    }
    
    public Gem(){
        generateGem();
    }
    
    private void generateGem(){
        
        BufferedImage src = null; 
          for(String f : spriteFiles){
            try {
                src = ImageIO.read(getClass().getResource(f));
            } catch (IOException ex) {
                Logger.getLogger(Gem.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            spriteImages.add(src);
          
          }
        
    }
     
}
