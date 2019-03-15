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
public class Exit extends Tile {
    public String[] spriteFiles = {
        "ext/ext1.png","ext/ext2.png","ext/ext3.png","ext/ext4.png"
    };
 
    public Exit(String name){
        super(name);
    }
    
    public Exit(){
        generateExit();
    }
    
    private void generateExit(){
        
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
