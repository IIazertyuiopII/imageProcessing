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
        
        //PGMImage I = new PGMImage(3,5);
        
        PGMImage Lena = new PGMImage(512,512);
        //Lena.fillImage();
        //Writer.writeToFile(Lena,"image");

        try {
            Lena = Reader.readFromFile("lena.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      Writer.writeToFile(Lena.generateHistogram(),"lenahist");
      //I.fillImage();
      //I.resize(2, 4);
      Lena.resize(200, 200);
      //System.out.println(Lena.getPixelArray().size());
      Writer.writeToFile(Lena,"lenamodif");
     
    }
}
