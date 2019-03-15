/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scrolltest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList; 
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import scrolltest.entity.Rockford;
import scrolltest.level_tiles.Boulder;
import scrolltest.level_tiles.Exit;
import scrolltest.level_tiles.Explode;
import scrolltest.level_tiles.Gem;
import scrolltest.level_tiles.Tile;

/**
 *
 * @author Win 7
 */
public class ScrollTest extends JPanel implements KeyListener {

    /**
     * @param args the command line arguments
     */
    
    public static Image img; 
    public void paint(Graphics g) {

        img = drawBorder();
        g.drawImage(img,(WORLD_SIZE_X / 2) - VIEWPORT_SIZE_X, (WORLD_SIZE_Y / 2) - VIEWPORT_SIZE_Y, this);

    }
     
    public static int WORLD_SIZE_X = 1600;
    public static int WORLD_SIZE_Y = 1600; 
    public static JFrame frame; 
    public int boulderDropTrack = 0;
    public Tile tile; 
    public Tile tileToCheck; 
    public Gem gems;
    public Boulder boulders;
    public Explode explosion;
    public Exit exit; 
    public boolean gameOver = false;
    public int tileTrack = 90;
    public static final String NAME = "Boulder Dash";
    public static int moveRockX = (32 * 8);
    public static int moveRockY = (32 * 15); 
    
    public int screenXTrack, screenXViewTrack;
    public int screenYTrack, screenYViewTrack;
    
    public static int VIEWPORT_SIZE_X = WORLD_SIZE_X / 2 + (32 * 2); 
    public static int VIEWPORT_SIZE_Y = WORLD_SIZE_Y / 2 + (32 * 10); 
    
    public long animateLoopLength = 1000L;
    public boolean timerStopped = false;
    public int keyStroke; 
    public int keyPressedCounter = 0;
    public int checkMove = 0;
    public TimerTask task = null;
    public int gemTimer = 0;
    public Timer timer = null;
    public Rockford ro = new Rockford("Rockford", new ArrayList<Image>());
    
    public ScrollTest(){
      
        tile = new Tile();
        tile.createTileCollection();
         
        gems = new Gem();
        boulders = new Boulder();
        explosion = new Explode();
        exit = new Exit();

        frame = new JFrame(NAME);
        frame.setSize((WORLD_SIZE_X / 4), (WORLD_SIZE_Y / 4));
        frame.addKeyListener(this); 
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true); 
       
      ro.x = moveRockX;
      ro.y = moveRockY;
      
      timerStart();
      
    }
    
    public synchronized void timerStart(){
        if(timerStopped){
            timerStopped = false; 
        }
        
            animateLoopLength = 150L;

        timer = new Timer();
        timer.schedule(new TimerTask() {
            
        @Override
        public void run() {  
            checkMove++; 
            gemTimer++; 
            frame.repaint();
            
            if(ro.actionNow == ro.actionNow.rightwalk){ 
                while(moveRockX % 32 != 0){ 
                        moveRockX += 4;
                        ro.x += moveRockX;
                        VIEWPORT_SIZE_X = VIEWPORT_SIZE_X + 4; 
                }
            }
            if(ro.actionNow == ro.actionNow.leftwalk){
                while(moveRockX % 32 != 0){ 
                        moveRockX -= 4;
                        ro.x -= moveRockX;
                        VIEWPORT_SIZE_X = VIEWPORT_SIZE_X - 4; 
                }
            }
            if(ro.actionNow == ro.actionNow.upwalk){
                while(moveRockY % 32 != 0){ 
                        moveRockY -= 4;
                        ro.y -= moveRockY;
                        VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y - 4; 
                }
            }
            if(ro.actionNow == ro.actionNow.downwalk){
                while(moveRockY % 32 != 0){ 
                        moveRockY += 4;
                        ro.y += moveRockY;
                        VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y + 4; 
                }
            }
            
        }
    }, 0, animateLoopLength);
        
    }
    
    public void timerStop(){
        timer.cancel();
        timer.purge();
        timerStopped = true;  
    }
    
    public static void main(String[] args) {
        
        ScrollTest st = new ScrollTest();
         
        frame.getContentPane().add(st);
       
    }

    private Image drawBorder(){
        
        BufferedImage bufferedImage = new BufferedImage(WORLD_SIZE_X, WORLD_SIZE_Y, BufferedImage.TYPE_INT_RGB);

       tile.caveTiles = tile.getCaveTiles();
      
       Image tileFill = null;
       for(Tile t: tile.caveTiles){
           if(t.tileName == "OrangeBlock"){
               tileFill = t.tileImage;
               break;
           }
       }
        
       //ORANGE BLOCK
       int XSIZE = 32;
       int YSIZE = 32;
       for(int x = 0; x < 50; x++){
           for(int y = 0; y < 3; y++){
               if(x < 2){
                   if(y == 1 || y == 2){
                       for(int i = 0; i < 50; i++){
                           for(int j = 0; j < 3; j++){
                              bufferedImage.getGraphics().drawImage(tileFill,(i * XSIZE), (j * YSIZE),null); 
                           }
                       }    
                   }
               }
               bufferedImage.getGraphics().drawImage(tileFill,(y * XSIZE), (x * YSIZE),null);
           }
       }
        
        for (int l = 0; l < 50; l++){ 
            for(int k = 46; k < 50; k++){ 
                 bufferedImage.getGraphics().drawImage(tileFill,(k * XSIZE), (l * YSIZE),null);
            }
        }
        
        for (int l = 46; l < 50; l++){ 
            for(int k = 0; k < 50; k++){ 
                 bufferedImage.getGraphics().drawImage(tileFill,(k * XSIZE), (l * YSIZE),null);
            }
        }
       
        for (int l = 3; l < 46; l++){ 
                 bufferedImage.getGraphics().drawImage(tileFill,(l * XSIZE), (YSIZE*2),null);
                 bufferedImage.getGraphics().drawImage(tileFill,(l * XSIZE), (YSIZE*45),null);
                 bufferedImage.getGraphics().drawImage(tileFill,(XSIZE*3), (YSIZE*l),null);
                 bufferedImage.getGraphics().drawImage(tileFill,(XSIZE*45), (YSIZE*l),null);
        }

        //VALUES TO ITERATE FOR THE REST OF THE LEVEL
        for(Tile t : tile.caveTiles){ 
            
            if(ro.actionNow == ro.actionNow.rightwalk){
                if(Math.abs(moveRockX - (t.x+128)) < 32 && (t.y+96) == moveRockY){
                    tileToCheck = t; 
                    mineCheck(t);
                }
            }
            if(ro.actionNow == ro.actionNow.leftwalk){
                if(Math.abs(moveRockX - (t.x+128)) < 32 && (t.y+96) == moveRockY){
                    tileToCheck = t;
                    mineCheck(t);
                }
            }
            if(ro.actionNow == ro.actionNow.upwalk){
                if(Math.abs(moveRockY - (t.y+96)) < 32 && (t.x+128) == moveRockX){
                    tileToCheck = t;
                    mineCheck(t);
                }
            }
            if(ro.actionNow == ro.actionNow.downwalk){
                if(Math.abs(moveRockY - (t.y+96)) < 32 && (t.x+128) == moveRockX){
                    tileToCheck = t;
                    mineCheck(t);
                }
            }
            
            if(moveRockX == t.x+128 && moveRockY == t.y+96){
                tileToCheck = t;
            }
             
            bufferedImage.getGraphics().drawImage(t.tileImage,t.x+128,t.y+96,null);
            
            if(t.tileName == "BlueMecha"){
                
                if(t.x == 1248 && t.y == 832){
                    
                    tile.minedTile(t,0);
                    
                    if(gemTimer % 4 == 0){
                            bufferedImage.getGraphics().drawImage(exit.spriteImages.get(0), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 1){
                        bufferedImage.getGraphics().drawImage(exit.spriteImages.get(1), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 2){    
                        bufferedImage.getGraphics().drawImage(exit.spriteImages.get(2), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 3){ 
                        bufferedImage.getGraphics().drawImage(exit.spriteImages.get(3), t.x+128, t.y+96, null);
                    }
                }
            }
            
            if(t.tileName == "Exit"){
                if(gemTimer % 4 == 0){
                            bufferedImage.getGraphics().drawImage(exit.spriteImages.get(0), t.x+128, t.y+96, null);
                }
                if(gemTimer % 4 == 1){
                    bufferedImage.getGraphics().drawImage(exit.spriteImages.get(1), t.x+128, t.y+96, null);
                }
                if(gemTimer % 4 == 2){    
                    bufferedImage.getGraphics().drawImage(exit.spriteImages.get(2), t.x+128, t.y+96, null);
                }
                if(gemTimer % 4 == 3){ 
                    bufferedImage.getGraphics().drawImage(exit.spriteImages.get(3), t.x+128, t.y+96, null);
                }
            }
            
            if(t.tileName == "Gem"){
                    
                    if(gemTimer % 4 == 0){
                        bufferedImage.getGraphics().drawImage(gems.spriteImages.get(0), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 1){
                        bufferedImage.getGraphics().drawImage(gems.spriteImages.get(1), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 2){    
                        bufferedImage.getGraphics().drawImage(gems.spriteImages.get(2), t.x+128, t.y+96, null);
                    }
                    if(gemTimer % 4 == 3){ 
                        bufferedImage.getGraphics().drawImage(gems.spriteImages.get(3), t.x+128, t.y+96, null);
                    }
                }
            
            if(t.tileName == "Boulder"){
                
                if(checkMove % 4 == 0){
                    bufferedImage.getGraphics().drawImage(boulders.spriteImages.get(0), t.x+128, t.y+96, null);
                }
                if(checkMove % 4 == 1){
                    bufferedImage.getGraphics().drawImage(boulders.spriteImages.get(1), t.x+128, t.y+96, null);
                }
                if(checkMove % 4 == 2){
                    bufferedImage.getGraphics().drawImage(boulders.spriteImages.get(2), t.x+128, t.y+96, null);
                }
                if(checkMove % 4 == 3){
                    bufferedImage.getGraphics().drawImage(boulders.spriteImages.get(3), t.x+128, t.y+96, null);
                }
                 
            }
            if(t.tileName == "Explode"){
                
                if(!gameOver){
                    if(checkMove % 5 == 0){
                        bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(0), t.x+128, t.y+96, null);
                    }
                    if(checkMove % 5 == 1){
                        bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(1), t.x+128, t.y+96, null);
                    }
                    if(checkMove % 5 == 2){
                        bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(2), t.x+128, t.y+96, null);
                    }
                    if(checkMove % 5 == 3){
                        bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(3), t.x+128, t.y+96, null);
                    }
                    if(checkMove % 5 == 4){
                        bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(4), t.x+128, t.y+96, null);
                        gameOver = true;
                    }
                }
                if(gameOver){
                    bufferedImage.getGraphics().drawImage(explosion.spriteImages.get(4), t.x+128, t.y+96, null);
                }
                
            }
            
            } 
            
        if(ro.actionNow == ro.actionNow.stationary){
            
            if((checkMove / 6) % 2 == 0){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(0), moveRockX, moveRockY, null);
            }
            if((checkMove / 6) % 2 == 1){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(1), moveRockX, moveRockY, null);
            }
        }
        if(ro.actionNow == ro.actionNow.leftwalk){
            if((checkMove / 3) % 3 == 0){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(2), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 3 == 1){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(3), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 3 == 2){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(4), moveRockX, moveRockY, null);
            }
        }
        if(ro.actionNow == ro.actionNow.rightwalk){
            if((checkMove / 3) % 3 == 0){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(5), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 3 == 1){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(6), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 3 == 2){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(7), moveRockX, moveRockY, null);
            }
            
        }
        if(ro.actionNow == ro.actionNow.downwalk){
            if((checkMove / 3) % 4 == 0){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(8), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 1){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(9), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 2){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(10), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 3){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(11), moveRockX, moveRockY, null);
            }
        }
        if(ro.actionNow == ro.actionNow.upwalk){
            if((checkMove / 3) % 4 == 0){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(12), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 1){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(13), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 2){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(14), moveRockX, moveRockY, null);
            }
            if((checkMove / 3) % 4 == 3){
                bufferedImage.getGraphics().drawImage(ro.spriteImages.get(15), moveRockX, moveRockY, null);
            }
        }
        
        for(Tile t : tile.caveTiles){
            
            int c = tile.caveTiles.indexOf(t);
            
            if(t.tileName == "Boulder"){
                
                if(tile.caveTiles.get(c+1).tileName == "MinedBlock"){
                        boulderCheck(t,true); 
                }
                if(tile.caveTiles.get(c).tileName == "Boulder"){
                    //gameover 
                    if(!gameOver){
                          
                        if(moveRockX - tile.caveTiles.get(c).x == 128 && moveRockY - tile.caveTiles.get(c).y == 96){
                            if(ro.actionNow == ro.actionNow.stationary){
                                tile.boulderSmoosh(t);
                            }
                            if(ro.actionNow == ro.actionNow.leftwalk){
                                if(tile.caveTiles.get(c-42).tileName == "MinedBlock"){
                                    tile.minedTile(t,-42);
                                }
                            }
                            if(ro.actionNow == ro.actionNow.rightwalk){
                                if(tile.caveTiles.get(c+42).tileName == "MinedBlock"){
                                    tile.minedTile(t, 42);
                                }
                            }
                            
                        }
                        if(tile.caveTiles.get(c-1).tileName == "Boulder" && tile.caveTiles.get(c).tileName == "Boulder" && tile.caveTiles.get(c+1).tileName == "Boulder"){
                            if(tile.caveTiles.get(c+43).tileName == "MinedBlock" && tile.caveTiles.get(c+42).tileName == "MinedBlock" && tile.caveTiles.get(c+41).tileName == "MinedBlock"){
                                //System.out.println("Boulder can fall into mined block c+43 ");
                                
                                if((moveRockX - tile.caveTiles.get(c+43).x == 128 && moveRockY - tile.caveTiles.get(c+43).y == 32) || 
                                        (moveRockX - tile.caveTiles.get(c+43).x == 128 && moveRockY - tile.caveTiles.get(c+43).y == 64)
                                        ){ 
                                    //DON"T FALL
                                }
                                else{ 
                                    tile.minedTile(t,43);
                                }
                                
                                if(moveRockX - tile.caveTiles.get(c+43).x == 128 && moveRockY - tile.caveTiles.get(c+43).y == 96){
                                     // tile.boulderSmoosh(t);
                                        tile.minedTile(t,43);
                                 }
                                
                            }
                            if(tile.caveTiles.get(c-41).tileName == "MinedBlock" && tile.caveTiles.get(c-42).tileName == "MinedBlock" && tile.caveTiles.get(c-43).tileName == "MinedBlock"){

                                if((moveRockX - tile.caveTiles.get(c-41).x == 128 && moveRockY - tile.caveTiles.get(c-41).y == 32) || 
                                        (moveRockX - tile.caveTiles.get(c-41).x == 128 && moveRockY - tile.caveTiles.get(c-41).y == 64)
                                        ){ 
                                    //DON"T FALL
                                }
                                else{ 
                                    tile.minedTile(t,-41);
                                }
                                
                                if(moveRockX - tile.caveTiles.get(c-41).x == 128 && moveRockY - tile.caveTiles.get(c-41).y == 96){
                                     // tile.boulderSmoosh(t);
                                        tile.minedTile(t,-41);
                                 } 
                        }
                    }           
                    }
                    
            }
        }
        }
        
      return bufferedImage; 
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
    public void keyYTileProcess(int m1, int c){
              
                            if(moveRockY >= (32 * 2) && moveRockY < (32 * 44)){
                                   
                                    moveRockY += m1;
                                    ro.y += moveRockY;
                                    
                                    if(tile.caveTiles.get(c).tileName == "BlueMecha"){
                                        if(Math.abs(tile.caveTiles.get(c).y - moveRockY) > 64){
                                            
                                            moveRockY -= m1;
                                            ro.y -= moveRockY;
                                            VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y - m1;
                                        }
                                    }
                                    if(tile.caveTiles.get(c).tileName == "Boulder"){
                                        if(Math.abs(tile.caveTiles.get(c).y - moveRockY) > 64){
                                            moveRockY -= m1;
                                            ro.y -= moveRockY;
                                            VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y - m1;
                                        }
                                    } 
                            }
                            
                        if(timerStopped){
                            timerStart();
                        }
    }
    
    public void alignViewportCheck(){
        
                if(Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X) != 0){

                    if(Math.abs(screenXTrack - moveRockX) > Math.abs(screenXViewTrack - VIEWPORT_SIZE_X)){
                        VIEWPORT_SIZE_X += (Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X));
                    }
                    if(Math.abs(screenXViewTrack - VIEWPORT_SIZE_X) > Math.abs(screenXTrack - moveRockX)){
                        VIEWPORT_SIZE_X -= (Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X));
                    }  
                }
            
                if(Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X) != 0){
                    //System.out.println("rock x " + Math.abs(screenXTrack - moveRockX) + " viewportx " + Math.abs(screenXViewTrack - VIEWPORT_SIZE_X));

                    if(Math.abs(screenXTrack - moveRockX) > Math.abs(screenXViewTrack - VIEWPORT_SIZE_X)){
                        VIEWPORT_SIZE_X += (Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X));
                    }
                    if(Math.abs(screenXViewTrack - VIEWPORT_SIZE_X) > Math.abs(screenXTrack - moveRockX)){
                        VIEWPORT_SIZE_X -= (Math.abs(screenXTrack - moveRockX) - Math.abs(screenXViewTrack - VIEWPORT_SIZE_X));
                    }
                    
                } 
        

                if(Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y) != 0){

                    if(Math.abs(screenYTrack - moveRockY) > Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y)){
                        VIEWPORT_SIZE_Y += (Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y));
                    }
                    if(Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y) > Math.abs(screenYTrack - moveRockY)){
                        VIEWPORT_SIZE_Y -= (Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y));
                    }
                    
                } 
                
                if(Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y) != 0){

                    if(Math.abs(screenYTrack - moveRockY) > Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y)){
                        VIEWPORT_SIZE_Y += (Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y));
                    }
                    if(Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y) > Math.abs(screenYTrack - moveRockY)){
                        VIEWPORT_SIZE_Y -= (Math.abs(screenYTrack - moveRockY) - Math.abs(screenYViewTrack - VIEWPORT_SIZE_Y));
                    }
                }
                
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
        keyPressedCounter++;
        
        screenXTrack = moveRockX; 
        screenXViewTrack = VIEWPORT_SIZE_X; 
        
        screenYTrack = moveRockY; 
        screenYViewTrack = VIEWPORT_SIZE_Y;
        
        boulderDropTrack = 0;
        int key = e.getKeyCode();
        keyStroke = e.getKeyCode();
        int c = tile.caveTiles.indexOf(tileToCheck);   
        
        if(c < 0){return;}
        
          if(ro.actionNow == ro.actionNow.stationary){ 
                timerStop();
                if (key == KeyEvent.VK_RIGHT) {
                    ro.actionNow = ro.actionNow.rightwalk;
                }
                if (key == KeyEvent.VK_LEFT) {
                    ro.actionNow = ro.actionNow.leftwalk;
                }
                if (key == KeyEvent.VK_DOWN) {
                   ro.actionNow = ro.actionNow.downwalk;
                }
                if (key == KeyEvent.VK_UP) {
                   ro.actionNow = ro.actionNow.upwalk;
                } 
                timerStart();
            }
                    if (key == KeyEvent.VK_RIGHT) {
                        
                        ro.actionNow = ro.actionNow.rightwalk;

                        if(moveRockY % 32 == 0){
                            if((WORLD_SIZE_X - VIEWPORT_SIZE_X) >= - 400){
                                VIEWPORT_SIZE_X = VIEWPORT_SIZE_X + 4;
                            }
                             
                            if(moveRockX >= (32 * 6) && moveRockX < (32 * 44)){
                                
                                if(tile.caveTiles.get(c).tileName != "BlueMecha"){
                                    
                                    moveRockX += 4;
                                    ro.x += moveRockX;
                                    
                                 
                                if(tile.caveTiles.get(c+42).tileName == "BlueMecha"){
                                    if(Math.abs(tile.caveTiles.get(c+42).x - moveRockX) > 96){
                                        moveRockX -= 4; 
                                        ro.x -= moveRockX;
                                        VIEWPORT_SIZE_X = VIEWPORT_SIZE_X - 4;
                                    }
                                }
                                 
                                if(tile.caveTiles.get(c+42).tileName == "Boulder"){
//                                    
                                    if(Math.abs(tile.caveTiles.get(c+42).x - moveRockX) > 96){
                                         
                                        if(tile.caveTiles.get(c+84).tileName == "MinedBlock" && tile.caveTiles.get(c+43).tileName != "MinedBlock"){
                                              
                                                boulderCheck(tile.caveTiles.get(c+42),false);
                                             
                                        } 
                                        else{ 
                                            moveRockX -= 4; 
                                            ro.x -= moveRockX;
                                            VIEWPORT_SIZE_X = VIEWPORT_SIZE_X - 4;
                                        }
                                    }
                                }
                                
                                if(tile.caveTiles.get(c+42).tileName == "Gem"){
                                    if(Math.abs(tile.caveTiles.get(c+42).x - moveRockX) > 96){ 
                                        
                                        mineCheck(tile.caveTiles.get(c+42));
                                        
                                    }
                                }
                                
                            }
                            }
                        }
                         
                        if(timerStopped){
                            timerStart();
                        }
                         
                         
                      
                    }

                    if (key == KeyEvent.VK_LEFT) {
                        //SCROLL WORLD
                        if(moveRockY % 32 == 0){
                            if((WORLD_SIZE_X - VIEWPORT_SIZE_X) <= ((WORLD_SIZE_X) / 2) - 4) {
                                VIEWPORT_SIZE_X = VIEWPORT_SIZE_X - 4;
                            }
                            //SCROLL PLAYER
                            if(moveRockX > (32 * 6) && moveRockX <= (32 * 44)){
                                 
                                if(tile.caveTiles.get(c).tileName != "BlueMecha"){
                                    moveRockX -= 4;
                                    ro.x -= moveRockX;
                                    
                                    //System.out.println(Math.abs(tile.caveTiles.get(c-43).x - moveRockX));
                                if(tile.caveTiles.get(c-42).tileName == "BlueMecha"){
                                    if(Math.abs(tile.caveTiles.get(c-42).x - moveRockX) > 96){
                                        moveRockX += 4; 
                                        ro.x += moveRockX;
                                         VIEWPORT_SIZE_X = VIEWPORT_SIZE_X + 4;
                                    }
                                }
                                if(tile.caveTiles.get(c-42).tileName == "Boulder"){
                                     
                                    if(Math.abs(tile.caveTiles.get(c-42).x - moveRockX) > 96){
                                        
                                        if(tile.caveTiles.get(c-84).tileName == "MinedBlock" && tile.caveTiles.get(c-41).tileName != "MinedBlock"){
                                            
                                           boulderCheck(tile.caveTiles.get(c-42),false);
                                            
                                        }
                                        else{
                                            moveRockX += 4; 
                                            ro.x += moveRockX;
                                             VIEWPORT_SIZE_X = VIEWPORT_SIZE_X + 4;
                                        }
                                        }
                                }
                                
                                if(tile.caveTiles.get(c-42).tileName == "Gem"){
                                    if(Math.abs(tile.caveTiles.get(c-42).x - moveRockX) > 96){
                                     
                                        mineCheck(tile.caveTiles.get(c-42));
                                        
                                    }
                                }
                                
                                }
                            }
                        }
                        
                        ro.actionNow = ro.actionNow.leftwalk;
                        
                        if(timerStopped){
                            timerStart();
                        }
                          
                    }

                    if (key == KeyEvent.VK_DOWN) {

                        if(moveRockX % 32 == 0){
                            if((WORLD_SIZE_Y - VIEWPORT_SIZE_Y) >= - 435){
                                VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y + 4;  
                            }
                            
                            keyYTileProcess(4,c+1);
                            ro.actionNow = ro.actionNow.downwalk;
                            
                        }
                         
                    }

                    if (key == KeyEvent.VK_UP) {

                        if(moveRockX % 32 == 0){
                            if((WORLD_SIZE_Y - VIEWPORT_SIZE_Y) <= (WORLD_SIZE_Y / 2) - 32){
                                
                                VIEWPORT_SIZE_Y = VIEWPORT_SIZE_Y - 4; 
                            }
                            ro.actionNow = ro.actionNow.upwalk;
                            keyYTileProcess(-4,c-1);                    
                        }
                        
                    }
                    
                    alignViewportCheck();
                    frame.repaint();
                        
    } 
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        keyPressedCounter = 0;
        
        
        
        if(moveRockX % 32 != 0){
              
            if(ro.actionNow == ro.actionNow.rightwalk){
                moveRockX += (32 - (moveRockX % 32)); 
                
                alignViewportCheck();
                
            }
            if(ro.actionNow == ro.actionNow.leftwalk){
                moveRockX -= ((moveRockX % 32) - 32); 
                
                alignViewportCheck();
            }
            
        }
        
        if(moveRockY % 32 != 0){
             
            if(ro.actionNow == ro.actionNow.upwalk){
                moveRockY += (32 - (moveRockY % 32));  
                
                alignViewportCheck();
                
            }
            if(ro.actionNow == ro.actionNow.downwalk){
                moveRockY -= ((moveRockY % 32) - 32);  
                
                alignViewportCheck();
                
            }
        }
         
        ro.actionNow = ro.actionNow.stationary;
         
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mineCheck(Tile t) {
        
        if(t.tileName == "OrangeRock"){ 
            tile.minedTile(t,0);
        }
        if(t.tileName == "Gem"){
            tile.minedTile(t,0);
        }
        if(t.tileName == "BlueMecha"){
            tile.minedTile(t,0);
        }
        
    }
 
    private void boulderCheck(Tile t, boolean boulderFall){
    
        Boulder bouldCompareToTile = null; 
        int bouldIndex = 0;
    
        if(keyPressedCounter > 0){
            return;
        }
        
        if(t.tileName == "Boulder"){ 
          
          for(Boulder b : tile.boulds){
                       
            if(t.x == b.x && t.y == b.y){ 
                bouldCompareToTile = b;
                bouldIndex = tile.boulds.indexOf(b);
            } 
          }  
           
          if(boulderFall == false){
                if(ro.actionNow == ro.actionNow.leftwalk){
                    tile.minedTile(t,-42);
                }
                if(ro.actionNow == ro.actionNow.rightwalk){
                    tile.minedTile(t, 42);
                }
          }
          if(boulderFall == true){
               
              //INITIAL ITERATION OF DROPPING BOULDERS 
              if(keyStroke == 0){
                  tile.minedTile(t, 1);
                  return;
              }
              
              if((moveRockY-96) - bouldCompareToTile.y == 32 && ((moveRockX-128) - bouldCompareToTile.x == 0) && bouldCompareToTile.boulderDropTrack > 1){
                  //System.out.println("Crash");
                  tile.minedTile(t, 1);
              }
              if((moveRockY-96) - bouldCompareToTile.y == 32 && ((moveRockX-128) - bouldCompareToTile.x == 0)){
                  //System.out.println("Under");
                  bouldCompareToTile.boulderDropTrack = 0;
              }
              
              if((moveRockY-96) - bouldCompareToTile.y > 32 || (moveRockX-128) - bouldCompareToTile.x != 0){
                  //System.out.println("Falling");
                  bouldCompareToTile.boulderDropTrack += 1; 
                  
                  if((moveRockX-128) - bouldCompareToTile.x == 0){ 
                      
                        tile.minedTile(t, 2); 
                         
                        t = tile.caveTiles.get(tile.caveTiles.indexOf(t)+2);
                        
                        tile.boulderSmoosh(t);
                        //gameOver = true;
                  }else{
                        //System.out.println("Only fall");
                        tile.minedTile(t, 1);
                  }
                  
              }
               
          }
            
        }
    
    }

    
}
