/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author rlebail
 */
public class PGMImage {
    
    public final static int greyScale = 255;
    public final static int lineLength = 70;
    
    private int l;
    private int h;
    private ArrayList<Integer> pixelArray;

    public PGMImage(int largeur, int hauteur){
        l=largeur;
        h=hauteur;
        pixelArray = new ArrayList<>();
        for(int i=0;i<l*h;i++) {pixelArray.add(200);}
        
    }
    
   public PGMImage(PGMImage I){
        l = I.getL();
        h = I.getH();
        pixelArray = I.getPixelArray();
    } 
       
    
    /**
     * @return the l
     */
    public int getL() {
        return l;
    }

    /**
     * @return the h
     */
    public int getH() {
        return h;
    }

    
    
    public int getAveragePixelValuesH(int i){
        
        int res = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(pixelArray.get(i));
        if(((int)i/l)<h-1)
        {list.add(pixelArray.get(i+l));}
        
        for(int j=0;j<list.size();j++)
        {
            res+=list.get(j);
        }
        res=Math.round(res/list.size());
        
        return res;
    }
     public int getAveragePixelValuesL(int i){
        
        int res = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(pixelArray.get(i));
        if((i%l)<l-1)
        {list.add(pixelArray.get(i+1));}
        
        for(int j=0;j<list.size();j++)
        {
            res+=list.get(j);
        }
        res=Math.round(res/list.size());
        
        return res;
    }
    
    /**
     * @return the pixelArray
     */
    public ArrayList<Integer> getPixelArray() {
        return pixelArray;
    }
    
    public void setPixelArray(ArrayList a) {
        pixelArray=(ArrayList<Integer>)a.clone();
    }
    
    public void fillImage() {
     
        for(int i=0; i<pixelArray.size(); i++){
            
            pixelArray.set(i, Math.round( (float) i*greyScale/pixelArray.size() ));
        }
        
    }
    
    public void Julia(double Pr, double Pi,double x0, double y0, double frameL, double frameH) {
     
        double x;
        double y;
        double tmp;
        int couleur;
        
        for(int i=0; i<pixelArray.size(); i++){
            x = x0-frameL/2+((double)(1+i%l))/l*frameL;
            y = y0-frameH/2+((double)(1+(int)i/l))/h*frameH;
            couleur=0;
            do{
                tmp=Pr+x*x-y*y;
                y=Pi+2*x*y;
                x=tmp;
                couleur++;
            }while(Math.sqrt(x*x+y*y)<=2 && couleur<greyScale);
            pixelArray.set(i,3*couleur%greyScale);
        }
        
    }
    
    public void Mandelbrot(double x0, double y0, double frameL, double frameH) {
     
        double Pr;
        double Pi;
        int couleur;
        double x;
        double y;
        double tmp;
        
        for(int i=0; i<pixelArray.size(); i++){
            
            Pr = x0-frameL/2+((double)(1+i%l))/l*(frameL);
            Pi = y0-frameH/2+((double)(1+(int)i/l))/h*(frameH);
            couleur=0;
            x = 0;
            y = 0;
            do{
                tmp=Pr+x*x-y*y;
                y=Pi+2*x*y;
                x=tmp;
                couleur++;
            }while(Math.sqrt(x*x+y*y)<=2 && couleur<greyScale);
            if(couleur==greyScale){
                pixelArray.set(i,greyScale);
            }
            else{
                pixelArray.set(i,0);
            }
        }
        
    }
    
    public PGMImage diff(PGMImage I){
    
    int diffVal = 0;
        
    if(!(l==I.getL() && h==I.getH())) {
        System.out.println("Les images n'ont pas la même taille");
        return I;
    }
        
    ArrayList<Integer> dataArray = I.getPixelArray();
    ArrayList<Integer> resArray = new ArrayList<>();
    
    for(int i=0; i<pixelArray.size(); i++){
       
       diffVal = pixelArray.get(i)-dataArray.get(i)>0?(pixelArray.get(i)-dataArray.get(i)):0;
       resArray.add(diffVal);
    }
    
    PGMImage delta = new PGMImage(l,h);
    delta.setPixelArray(resArray);
    
    return delta;           
    }
    
    public PGMImage seuil(int s){
       ArrayList<Integer> resArray = new ArrayList<>();
       int diffVal = 0;
       
       for(int i=0; i<pixelArray.size(); i++){
           diffVal =  pixelArray.get(i)>s?255:0;
           resArray.add(diffVal);
       } 
       
    setPixelArray(resArray);
    return this;
    }
    
    public PGMImage seuil(){
        seuil(127);
    return this;
    }
    
    public int count (Integer O, ArrayList<Integer> A){
        int res = 0;
        ArrayList<Integer> Acopy = (ArrayList <Integer>)A.clone();
        while (Acopy.contains(O)){
            res++;
            Acopy.remove(O);
        }
        return res;
    }
    
    
    public PGMImage generateHistogram() {
        
       ArrayList<Integer> freqArrays = new ArrayList<>();
       int maxFreq = 0;
       
       for(int i=0; i<256; i++){
           freqArrays.add(0);
       } 
       
       for(int i=0; i<l*h; i++){           
           freqArrays.set(pixelArray.get(i),1+freqArrays.get(pixelArray.get(i)));
           
           if(freqArrays.get(pixelArray.get(i))>maxFreq){
               maxFreq=freqArrays.get(pixelArray.get(i));
           }
       } 
       
        //System.out.println(maxFreq);
        
       ArrayList<Integer> histPixelArray = new ArrayList<>();
       
       for(int i=0;i<256*maxFreq;i++){
           histPixelArray.add(0);
       }
       
       
       for(int i=0; i<256; i++){
           for(int j=0; j<maxFreq; j++){
             
               
            if(j<freqArrays.get(i))
            {histPixelArray.set(maxFreq*i+j,0);}
            else
            {histPixelArray.set(maxFreq*i+j,255);}
            
           }
       }
        
       PGMImage histImage = new PGMImage(maxFreq,256); 
       histImage.setPixelArray(histPixelArray);
       
       return histImage;        
    }
    
    public void resizeH(int hauteur)
    { 
        
        int oldHauteur = h;
        int oldLargeur = l;
        ArrayList<Integer> oldArray = (ArrayList<Integer>)pixelArray.clone();
        
        ArrayList<Integer> newArray = new ArrayList<>();
        
        int deltaH = oldHauteur-hauteur;

        if(deltaH==0){return;}
        if(deltaH>0){ //réduction
        ArrayList<Integer> delIndicesArray = new ArrayList<>();    
        
        for(int i=0;i<deltaH;i++){
            delIndicesArray.add(Math.round((float) i*oldHauteur/deltaH));
        }    
        
        
        
        for(int i=0;i<oldHauteur*oldLargeur;i++){
           if(!delIndicesArray.contains((int)i/oldLargeur)){
               newArray.add(oldArray.get(i));
           }             
        }
            
        }
        else{ //agrandissement
        
         newArray = (ArrayList<Integer>)oldArray.clone();
            
         deltaH = -1*deltaH;   
         ArrayList<Integer> addIndicesArray = new ArrayList<>();    
        
        int valueToAdd; 
        for(int i=0;i<deltaH;i++){
            valueToAdd = Math.round((float) i*oldHauteur/deltaH) < oldHauteur ? Math.round((float) i*oldHauteur/deltaH) : oldHauteur-1;
            addIndicesArray.add(valueToAdd);
        }    
                
        ArrayList<Integer> newPixelLine = new ArrayList<>();
        for(int i=oldHauteur*oldLargeur-1;i>=0;i--){
            int currentLineInd = (int)i/oldLargeur; 
            if(addIndicesArray.contains(currentLineInd)){ 
                for(int k=0;k<l;k++){
                    newPixelLine.add(getAveragePixelValuesH(i));
                    i--;
                }
                i++;
                //System.out.println(newPixelLine);
                Collections.reverse(newPixelLine);
                for(int n=0;n<count(currentLineInd, addIndicesArray);n++){
                    newArray.addAll(i+(l), newPixelLine);
                }
                newPixelLine.clear();
           }
        }        
        }
        
        //System.out.println(newArray.size());
        setH(hauteur);
        setPixelArray(newArray);
    }
    
    public void resizeL(int largeur)
    {
       int oldHauteur = h;
        int oldLargeur = l;
        ArrayList<Integer> oldArray = (ArrayList<Integer>)pixelArray.clone();
        
        ArrayList<Integer> newArray = new ArrayList<>();
        
        int deltaL = oldLargeur-largeur;

        if(deltaL==0){return;}
        if(deltaL>0){ //réduction
        ArrayList<Integer> delIndicesArray = new ArrayList<>();    
            
        for(int i=0;i<deltaL;i++){
            delIndicesArray.add(Math.round((float) i*oldLargeur/deltaL));
        }    
        
        for(int i=0;i<oldHauteur*oldLargeur;i++){
           if(!delIndicesArray.contains(i%oldLargeur)){
               newArray.add(oldArray.get(i));
           }             
        }
            
            
        }
        else{
            newArray = (ArrayList<Integer>)oldArray.clone();

             deltaL = -1*deltaL;   
             ArrayList<Integer> addIndicesArray = new ArrayList<>();    

            int valueToAdd; 
            for(int i=0;i<deltaL;i++){
                valueToAdd = Math.round((float) i*oldHauteur/deltaL) < oldLargeur ? Math.round((float) i*oldHauteur/deltaL) : oldLargeur-1;
                addIndicesArray.add(valueToAdd);
            }  
            
            //System.out.println(addIndicesArray);
            
            for(int i=oldHauteur*oldLargeur-1;i>=0;i--){
                int currentColInd = i%oldLargeur;
                if(addIndicesArray.contains(currentColInd)){
                   //System.out.println("indice : "+i);
                   for(int n=0;n<count(currentColInd, addIndicesArray);n++){
                      newArray.add(i+1, getAveragePixelValuesL(i));
                   }
               } 

            }  
        }
        //System.out.println(newArray.size());
        setL(largeur);
        setPixelArray(newArray);

    }       
            
    public void resize(int largeur, int hauteur){
    
        resizeL(largeur);
        resizeH(hauteur);

    }

    /**
     * @param l the l to set
     */
    public void setL(int l) {
        this.l = l;
    }

    /**
     * @param h the h to set
     */
    public void setH(int h) {
        this.h = h;
    }
}
