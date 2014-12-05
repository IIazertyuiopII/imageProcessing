/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *Gère l'écriture dans fichier
 * @author gmenudie & oliviernguyen
 */
public class Writer {

    /**
     *Crée un fichier .pgm a partir d'un PGMImage
     * @param image
     */
    public static void writeToFile(PGMImage image, String filename) {
        
        int compteur=0; //Permet de compter le nombre de caractères insérés.

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(filename+".pgm"));
            bw.write("P2 \n#\n");
            bw.write(image.getL()+" "+image.getH()+"\n");
            bw.write(Integer.toString(PGMImage.greyScale)+"\n");
            
            for(int i=0;i<(image.getL()*image.getH());i++ ){
                bw.write(Integer.toString(image.getPixelArray().get(i))+" ");
                compteur+= 4;
                if((i+1)%image.getL()==0 || compteur>70)
                {
                    bw.newLine();
                    compteur=0;
                }
            }   
            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
