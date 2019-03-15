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
public class Explode extends Tile {
     
    public String[] spriteFiles = {
        "exp/exp1.png","exp/exp2.png","exp/exp3.png","exp/exp4.png","tile/minedblock.png"
    };
    
    public Explode(String name, Image image){
        super(name,image);
    }
    
    public Explode(){
        generateExplosion();
    }
    
    private void generateExplosion(){
        
        BufferedImage src = null; 
          for(String f : spriteFiles){
            try {
                src = ImageIO.read(Explode.class.getResource(f));
            } catch (IOException ex) {
                Logger.getLogger(Explode.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            spriteImages.add(src);
          
          }
        
    }
    
    
}
