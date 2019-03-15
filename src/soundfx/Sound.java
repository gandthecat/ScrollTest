/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package soundfx;
 
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author Win 7
 */
public abstract class Sound {
    
    protected static final int SAMPLE_RATE = 16 * 1024;
    public static int ms = 250;
    public static int[] bufferFill() {
       
       Random random = new Random(); 
       int[] output = new int[4096];
       int outputSize = 4096;
       int val = 0;
       
       for (int i = 0; i < outputSize; i++){
            
           val = random.nextInt(180)+i;
           output[i] += (val)*i;
           
       }
        
       return output;
   }



   public static void main(String[] args) throws LineUnavailableException {
       final AudioFormat af = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
       SourceDataLine line = AudioSystem.getSourceDataLine(af);
       line.open(af, SAMPLE_RATE);
       line.start();
 
       byte[] outBuffer = new byte[4096];
        
       int[] output = bufferFill();
       
       for(int x = 0; x < 4096; x++)  {
          
           outBuffer[x] = (byte)output[x];
           line.write(outBuffer, 0, 4096);
            
       } 

       line.drain();
       line.close();
    }

    
    public static class Break extends Sound{
    
    }
     
    private SourceDataLine dataLine;
    private ArrayList<Sound> sounds = new ArrayList<Sound>();
    
    public Sound(){
    
         
        try
        {
            AudioFormat audioFormat = new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
            dataLine = AudioSystem.getSourceDataLine(audioFormat);
            dataLine.open(audioFormat, SAMPLE_RATE/10);
            dataLine.start();

            Thread thread = new Thread((Runnable) this);
            thread.setDaemon(true);
            thread.start();
        }
        catch (Throwable e)
        {
            e.printStackTrace(); 
        }
    
    }
    
}
