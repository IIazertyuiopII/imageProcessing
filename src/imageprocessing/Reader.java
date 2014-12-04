/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author gmenudie & Olivier Nguyen
 */
public class Reader {
    
     /**
     *Crée une PGMImage à partir d'un fichier .pgm
     *
     */
    public static PGMImage readFromFile(String filename) throws FileNotFoundException, IOException {

       
            String ligne;
            BufferedReader br = new BufferedReader(new FileReader(filename));  
            StringTokenizer tokenizer;
            int i=0;            
            int l,h;
            
            //On saute les deux premières lignes
            br.readLine();
            br.readLine();
            
            //On récupère largeur et hauteur
            ligne=br.readLine();
            tokenizer  = new StringTokenizer(ligne," ");
            l = Integer.parseInt(tokenizer.nextToken());
            h = Integer.parseInt(tokenizer.nextToken());
            
            //On crée l'objet PGMImage
            PGMImage image = new PGMImage(l,h);
            
            //On saute la ligne de niveau de gris max
            br.readLine();
            
            //On remplit le pixelArray
            while((ligne = br.readLine())!=null){
                tokenizer  = new StringTokenizer(ligne," \t");
                while(tokenizer.hasMoreTokens()){
                    image.getPixelArray().add(i,Integer.parseInt(tokenizer.nextToken()));
                    i++;                    
                }
            }            
                       
            br.close();
            
            return image;
        
    }
    
    
    //public static 
}

    

