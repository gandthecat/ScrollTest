/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrolltest.level_tiles;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Win 7
 */
public class Tile {
    public String tileName;
    public Image tileImage;
    public int x; 
    public int y; 
    public ArrayList<Tile> caveTiles = new ArrayList<Tile>();
    public ArrayList<Image> spriteImages = new ArrayList<Image>();
    public ArrayList<Boulder> boulds = new ArrayList<Boulder>();
    int[][] multi = new int[][]{
    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 4, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 4, 0, 0, 0, 0, 1, 2, 0, 0, 4, 3, 2, 0, 4, 4, 2, 0, 0, 3, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 2, 0, 4, 2, 0, 1, 0, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 4, 0, 4, 2, 4, 1, 0, 3, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 4, 4, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 2, 0, 0, 0, 0, 4, 1, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 2, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 3, 4, 4, 0, 0, 0, 1, 0, 0, 0, 4, 2, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 3, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 2, 2, 0, 0, 0, 1, 2, 0, 0, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 4, 2, 0, 0, 4, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 2, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 4, 0, 0, 0, 2, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 4, 4, 2, 0, 1, 4, 0, 0, 0, 0, 0, 1, 0, 0, 4, 0, 4, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 4, 0, 1, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 4, 1, 4, 0, 0, 0, 0, 0, 1, 3, 4, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 4, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 4, 0, 1, 0, 0, 0, 4, 0, 4, 1, 0, 0, 0, 4, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 4, 4, 2, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 3, 4, 1, 0, 0, 4, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 4, 4, 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 4, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 3, 4, 0, 0, 4, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 4, 1, 0, 0, 2, 0, 0, 2, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 4, 0, 2, 1, 0, 4, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 4, 4, 0, 0, 4, 1, 0, 4, 2, 0, 0, 0, 1, 0, 4, 0, 4, 4, 4, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 4, 0, 1, 3, 4, 4, 4, 0, 0, 1, 4, 0, 0, 4, 4, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 4, 0, 1, 0, 0, 3, 3, 3, 4, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 2, 0, 0, 4, 0, 0, 1, 4, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 2, 0, 0, 0, 0, 1, 3, 4, 4, 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3, 1, 0, 4, 4, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 4, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 4, 0, 4, 0, 4, 4, 4, 0, 0, 0, 0, 0, 0, 1, 0, 4, 4, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 3, 0, 1, 0, 4, 4, 4, 2, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 2, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 4, 4, 4, 2, 0, 0, 4, 2, 0, 1, 0, 0, 0, 0, 0, 4, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1, 0, 1, 0, 0, 4, 0, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    }; 
    
    public Tile(String tileName, Image image){
        this.tileName = tileName;
        this.tileImage = image;  
    }
    
    public Tile(String tileName, Image image, int x, int y){
        this.tileName = tileName;
        this.tileImage = image;  
        this.x = x;
        this.y = y; 
    }
    
    public Tile(){
        tileName = "initialise";
        tileImage = null; 
    }
    
    public Tile(String tileName){
        this.tileName = tileName; 
    }
    
    public Tile(String tileName, int x, int y){
        this.tileName = tileName;
        this.x = x; 
        this.y = y; 
    }
    
    public void addCaveTile() {
        caveTiles.add(this);
    }
    
    public void createTileCollection(){
          
        try {
            caveTiles.add(new Tile("BlueMecha", ImageIO.read(getClass().getResource("tile/bluemecha.png"))));
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            caveTiles.add(new Tile("OrangeBlock", ImageIO.read(getClass().getResource("tile/orangeblock.png"))));
        } catch (IOException ex) {
            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < 41; i++){
            for(int j = 0; j < 42; j++){
                //System.out.println(multi[i][j]);
                if(multi[i][j] == 0){
                    try {
                        caveTiles.add(new Tile("OrangeRock", ImageIO.read(getClass().getResource("tile/orangeblock.png")), i * 32, j * 32));
                    } catch (IOException ex) {
                        Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(multi[i][j] == 1){
                    try {
                        caveTiles.add(new Tile("BlueMecha", ImageIO.read(getClass().getResource("tile/bluemecha.png")), i * 32, j * 32));
                    } catch (IOException ex) {
                        Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(multi[i][j] == 2){
                    try {
                        caveTiles.add(new Tile("MinedBlock", ImageIO.read(getClass().getResource("tile/minedblock.png")), i * 32, j * 32));
                    } catch (IOException ex) {
                        Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(multi[i][j] == 3){
                    caveTiles.add(new Tile("Gem", i * 32, j * 32));
                }
                if(multi[i][j] == 4){
                    caveTiles.add(new Tile("Boulder", i * 32, j * 32));
                    boulds.add(new Boulder(caveTiles.size(),0,"Boulder", i * 32, j * 32));
                }
                
                if(multi[i][j] == 8){
                     caveTiles.add(new Tile("Rockford", null));
                }
            }
        }
    }
    
    public void minedTile(Tile tile, int index){
         
        for(int x = 0; x < caveTiles.size(); x++){
            if(caveTiles.get(x) == tile){
                
                if(tile.tileName == "Boulder" || tile.tileName == "Gem" || tile.tileName == "OrangeRock"){
                    
                    for(Boulder b : boulds){
                        if(b.x == tile.x && b.y == tile.y){
                            Tile b1 = caveTiles.get(caveTiles.indexOf(tile));
                            Tile b2 = caveTiles.get(x+(index)); 
                            Boulder b3 = new Boulder();
                            b3.x = b2.x; 
                            b3.y = b2.y; 
                            b3.tileName = "Boulder";
                            b3.boulderIndex = x+index;
                            b3.boulderDropTrack = 0;

                            boulds.set(boulds.indexOf(b), b3);
                        }
                    }
                     
                        int x1 = caveTiles.get(x+(index)).x;
                        int y1 = caveTiles.get(x+(index)).y; 
                        Tile t1 = new Tile("Boulder",x1,y1);
                         
                         caveTiles.set(x+(index), t1);
                          
                        setCaveTilesCollection(caveTiles);
                         
                        try {
                            caveTiles.get(x).tileImage = ImageIO.read(getClass().getResource("tile/minedblock.png"));
                            caveTiles.get(x).tileName = "MinedBlock"; 
                        } catch (IOException ex) {
                            Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                }
                
                if(tile.tileName == "BlueMecha"){
                    int x1 = caveTiles.get(x+(index)).x;
                    int y1 = caveTiles.get(x+(index)).y; 
                        Tile t1 = new Tile("Exit",x1,y1);
                         
                         caveTiles.set(x+(index), t1);
                          
                        setCaveTilesCollection(caveTiles);
                        
                        caveTiles.get(x).tileName = "Exit";  
                }
                 
                
            }
        }
    }
    
    public void boulderSmoosh(Tile tile){
        for(int x = 0; x < caveTiles.size(); x++){
            if(caveTiles.get(x) == tile){
                
                int[] explodeArea = {0,-1,1,-41,-42,-43,41,42,43};
                
                for(int i : explodeArea){
                    int x1 = caveTiles.get(x+(i)).x;
                    int y1 = caveTiles.get(x+(i)).y;
                    //System.out.println(x1 + " " + y1 + " " + caveTiles.get(x+42).tileName);
                    Tile t1 = new Tile("Explode",x1,y1);
                     caveTiles.set(x+(i), t1);
                }
                
                setCaveTilesCollection(caveTiles);
            }
        }
    }

    public void setCaveTilesCollection(ArrayList<Tile> cavet) {
        this.caveTiles = cavet;
    }
    
    public ArrayList<Tile> getCaveTiles(){
        return this.caveTiles;
    }
     
    public void setBoulds(ArrayList<Boulder> b) {
        this.boulds = b;
    }
    
    public ArrayList<Boulder> getBoulds(){
        return this.boulds;
    }
    
}
