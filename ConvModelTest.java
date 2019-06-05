/*
 * Name:     Iuliia Buniak
 *
 * Course:   CS-13, Spring 2019
 *
 * Date:     05/14/19
 *
 * Filename: ConvModel.java
 *
 * Purpose:  To provide JUnit tests for currency converter application
 */
 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ConvModelTest {
 
    @Test
    public void testGetConv1(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getConv("USD", "EUR", "10"));
    }
  
    @Test
    public void testGetConv2(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getConv("EUR", "USD", "12.34"));
    }
  
    @Test
    public void testGetConv3(){
        ConvModel c = new ConvModel();
        assertEquals(false, c.getConv("USD", "EUR", "abc"));
    }
    
    @Test
    public void testGetConv4(){
        ConvModel c = new ConvModel();
        assertEquals(false, c.getConv("USD", "EUR", "0"));
    }
    
    @Test
    public void testGetConv5(){
        ConvModel c = new ConvModel();
        assertEquals(false, c.getConv("USD", "EUR", "-10"));
    }
  
    @Test
    public void testGetConvAmount(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getConv("USD", "EUR", "100"));
        assertTrue(0 < Double.parseDouble(c.getConvAmount()) && Double.parseDouble(c.getConvAmount()) <= 150);
       
    } 
    
    @Test
    public void testGetCRate(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getConv("USD", "EUR", "100"));
        assertTrue(0 < Double.parseDouble(c.getCRate()) && Double.parseDouble(c.getCRate()) <= 2);
    }
    
    @Test
    public void testTime(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getConv("USD", "EUR", "10")); 
        assertNotNull(c.getTime());   
    }
    
    @Test
    public void testGetHistory1(){
         ConvModel c = new ConvModel();
         assertEquals(true, c.getHistory("USD", "EUR", "2013-03-16"));
    }
    
    @Test
    public void testGetHistory2(){
         ConvModel c = new ConvModel();
         assertEquals(false, c.getHistory("USD", "EUR", "2013/03/16"));
    }

    @Test
    public void testGetHistory3(){
         ConvModel c = new ConvModel();
         assertEquals(false, c.getHistory("USD", "EUR", "1977-02-09"));
    }

    @Test
    public void testGetHistory4(){
         ConvModel c = new ConvModel();
         assertEquals(false, c.getHistory("USD", "EUR", "“20007-22-23”"));
    }
    
    
    @Test
    public void testGetHistory5(){
         ConvModel c = new ConvModel();
         assertEquals(false, c.getHistory("USD", "EUR", "abcdefg"));
    }
    
    @Test
    public void testGetHistory6(){
         ConvModel c = new ConvModel();
         assertEquals(true, c.getHistory("RUB", "UAH", "2019-01-01"));
    }


    @Test
    public void testGetHistory7(){
         ConvModel c = new ConvModel();
         assertEquals(false, c.getHistory("CAD", "UAH", "2030-01-01"));
    }
    
    
    @Test 
    public void testGetHRate1(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getHistory("CAD", "UAH", "2013-03-16"));
        assertEquals (true, c.getHRate("UAH").equals("7.964507"));      
       
    }
    
     @Test 
    public void testGetHRate2(){
        ConvModel c = new ConvModel();
        assertEquals(true, c.getHistory("USD", "UAH", "1999-01-01"));
        assertEquals ("No rate available for", c.getHRate("UAH"));      
       
    }
    
}