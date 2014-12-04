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
        
      PGMImage I = new PGMImage(3,5);
        
        PGMImage Lena = null;
        try {
            Lena = Reader.readFromFile("lena.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      //I.fillImage();
      //I.resize(2, 4);
      Lena.resize(50, 50);
      Writer.writeToFile(Lena.generateHistogram(),"lenahist");
      Writer.writeToFile(Lena,"lenamodif");
     
    }
}
