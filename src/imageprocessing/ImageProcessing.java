/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rlebail
 */
public class ImageProcessing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Test des readers and writers
                
        PGMImage Lena = new PGMImage(4000,4000);
        //Lena.Mandelbrot(-1,0,3,3);
        //Writer.writeToFile(Lena,"image");
        Lena.Julia(-0.8,0.156,0,0,3,3);
        Writer.writeToFile(Lena,"image2");
/*
       try {
            Lena = Reader.readFromFile("lena.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
    
      Writer.writeToFile(Lena.generateHistogram(),"lenahist");
*/        
/*
      Lena.resize(200,200);
      Writer.writeToFile(Lena,"lenared");
      Lena.resize(1000,1000);
      Writer.writeToFile(Lena,"lenaagr");
*/
      
//Lena.seuil();
      //Writer.writeToFile(Lena,"seuild");
     
    }
}
