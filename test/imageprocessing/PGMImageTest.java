/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author marc
 */
public class PGMImageTest {
       int x;
       int y;
       PGMImage img, hist, lena200,lena1024, lenaSeuil, lenaSeuil100, noire, lena;
    
    
    public PGMImageTest() {
        x = 20;
        y = 30;
        img = new PGMImage(x,y);
        noire = new PGMImage(x,y);
        hist = new PGMImage(2716,256);
        try {
            hist = Reader.readFromFile("test/material/lenahist.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lena = new PGMImage(2716,256);
        try {
            lena = Reader.readFromFile("test/material/lena.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        lena200 = new PGMImage(200,200);
        try {
            lena200 = Reader.readFromFile("test/material/lena200.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        lena1024 = new PGMImage(1024,1024);
        try {
            lena1024 = Reader.readFromFile("test/material/lena1024.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lenaSeuil = new PGMImage(1024,1024);
        try {
            lenaSeuil = Reader.readFromFile("test/material/lenaSeuil.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lenaSeuil100 = new PGMImage(1024,1024);
        try {
            lenaSeuil100 = Reader.readFromFile("test/material/lenaSeuil100.pgm");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessing.class.getName()).log(Level.SEVERE, null, ex);
        }
        //faire des boucle ave plusieurs images ?
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        img = new PGMImage(x,y);

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getL method, of class PGMImage.
     */
    @Test
    public void testGetL() {
        System.out.println("getL");
        assertEquals(img.getL(), x);

    }

    /**
     * Test of getH method, of class PGMImage.
     */
    @Test
    public void testGetH() {
        System.out.println("getH");
        assertEquals(img.getH(), y);
    }

    /**
     * Test of getPixelArray method, of class PGMImage.
     */
    @Ignore
    @Test
    public void testGetPixelArray() {
        System.out.println("getPixelArray");
        PGMImage instance = null;
        ArrayList<Integer> expResult = null;
        ArrayList<Integer> result = instance.getPixelArray();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPixelArray method, of class PGMImage.
     */
    @Ignore
    @Test
    public void testSetPixelArray() {
        System.out.println("setPixelArray");
        ArrayList a = null;
        PGMImage instance = null;
        instance.setPixelArray(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fillImage method, of class PGMImage.
     */
    @Test
    public void testFillImage() {
        System.out.println("fillImage");
        img.fillImage();
        fail("The test case is a prototype.");
    }

    /**
     * Test of diff method, of class PGMImage.
     */
    @Test
    public void testDiff() {
        System.out.println("diff");
        assertEquals(img.diff(noire).getPixelArray(),noire.getPixelArray());
        img.fillImage();
        assertThat(img.diff(noire), not(noire));
        fail("testDiff");

    }

    /**
     * Test of seuil method, of class PGMImage.
     */
    @Test
    public void testSeuil_int() {
        System.out.println("seuil");
        assertEquals(lenaSeuil100.diff(lena.seuil(100)).getPixelArray(),noire.getPixelArray());

    }

    /**
     * Test of seuil method, of class PGMImage.
     */
    @Test
    public void testSeuil_0args() {
        assertEquals(lenaSeuil.diff(lena.seuil()).getPixelArray(),noire.getPixelArray());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateHistogram method, of class PGMImage.
     */
    @Test
    public void testGenerateHistogram() {
        System.out.println("generateHistogram");
        assertEquals(hist.diff(lena.generateHistogram()).getPixelArray(),noire.getPixelArray());
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resizeH method, of class PGMImage.
     */
    @Test
    public void testResizeH() {
        System.out.println("resizeH");
        int hauteur = 0;
        PGMImage instance = null;
        instance.resizeH(hauteur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resizeL method, of class PGMImage.
     */
    @Test
    public void testResizeL() {
        System.out.println("resizeL");
        int largeur = 0;
        PGMImage instance = null;
        instance.resizeL(largeur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resize method, of class PGMImage.
     */
    @Test
    public void testResize() {
        System.out.println("resize");
        int largeur = 0;
        int hauteur = 0;
        PGMImage instance = null;
        instance.resize(largeur, hauteur);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setL method, of class PGMImage.
     */
    @Test
    public void testSetL() {
        System.out.println("setL");
        int l = 0;
        PGMImage instance = null;
        instance.setL(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setH method, of class PGMImage.
     */
    @Test
    public void testSetH() {
        System.out.println("setH");
        int h = 0;
        PGMImage instance = null;
        instance.setH(h);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
