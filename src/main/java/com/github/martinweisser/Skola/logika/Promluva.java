package com.github.martinweisser.Skola.logika;
import java.util.*;

/**
 *  Třída Promluva - obsahuje jednotlive promluvy
 *  jednotlivych postav.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class Promluva
{
    
    private static final String String = null;
	HerniPlan plan = new HerniPlan();
	private java.lang.String ucitelPromluva;
           
    /**
     * Tato specifická metoda vrací rozhovor dvou osob a to způsobem,
     * že daný písmena daného Stringu převede do pole a pak každá pole,
     * jedno za druhým, vypíše s tím, že mezi jednotlivými výpisy
     * je pauza.
     */
    public String vratPromluvuBufetackaDva()
    {
        System.out.println("Ty: ");
        String ty = "Pani bufetacko! Ve skole hori! Je potreba, abyste ihned opustila budovu!\n" +
                    "Sejdeme se venku!" + "\n";
        try {
            char[] tvuj = ty.toCharArray();
            for (int i = 0; i < tvuj.length; i++) {
                System.out.print(tvuj[i]);
                Thread.sleep(100);
            }}
        catch(InterruptedException ex){
             Thread.currentThread().interrupt();
        }
                
        System.out.println("Bufetacka: ");
        String bufetacka = "To je nestesti!\n" +
                           "Ihned utikam k vychodu! Dekuji za upozorneni!" + "\n";
        
        try {
            char[] buf = bufetacka.toCharArray();
            for (int i = 0; i < buf.length; i++) {
                System.out.print(buf[i]);
                Thread.sleep(100);
            }}
        catch(InterruptedException ex){
             Thread.currentThread().interrupt();
        }
                
        plan.setBufetacka();
        return null;       
        
    }
    
    /**
     * Tato specifická metoda vrací rozhovor dvou osob a to způsobem,
     * že daný písmena daného Stringu převede do pole a pak každá pole,
     * jedno za druhým, vypíše s tím, že mezi jednotlivými výpisy
     * je pauza.
     */
    public String vratPromluvuUcitelDva()
    {
        System.out.println("Ty: ")       ;
        String ty = "Pane uciteli! Ve skole hori! Je potreba, abyste okamzite opustil budovu!" + 
                    "\nSejdeme se venku!" + "\n" ;
        try {
            char[] tvuj = ty.toCharArray();
            for (int i = 0; i < tvuj.length; i++) {
                System.out.print(tvuj[i]);
                Thread.sleep(100);
            }}
        catch(InterruptedException ex){
             Thread.currentThread().interrupt();
        }
        
        
        System.out.println("Ucitel: ");
        String ucitel = "Nezbedna prace elektroinstalateru a pak to takhle dopada!"
                        + "\nDekuji za varovani! Okamzite bezim k vychodu! Opatruj se!" + "\n" ;
        try {
            char[] uc = ucitel.toCharArray();
            for (int i = 0; i < uc.length; i++) {
                System.out.print(uc[i]);
                Thread.sleep(100);
            }}
        catch(InterruptedException ex){
             Thread.currentThread().interrupt();
        }
                
        plan.setUcitel();
        return null;
    }
    
    /**
     * Upravená metoda kvůli vypisován
     */
     
    public String vratPromluvuUcitel()
    {
        String ty = "Ty: ";
        String tvojePromluva = "Pane uciteli! Ve skole hori! Je potreba, abyste okamzite opustil budovu!";
        String ucitelPromluva = "Ucitel: Nezbedna prace elektroinstalateru a pak to takhle dopada! Dekuji za varovani! Okamzite bezim k vychodu! Opatruj se!";
        
        plan.setUcitel();
        
        String promluvaUcitel = ty + tvojePromluva + ucitelPromluva;
        return promluvaUcitel;
    }
    
    /**
     * Upravená metoda kvůli vypisován
     */
     
    public String vratPromluvuBufetacka()
    {
        String ty = "Ty: ";
        String tvojePromluva = "Pani bufetacko! Ve skole hori! Je potreba, abyste ihned opustila budovu!";
        String ucitelPromluva = "Bufetacka: Ihned utikam k vychodu! Dekuji za upozorneni!";
        
        plan.setUcitel();
        
        String promluvaUcitel = ty + tvojePromluva + ucitelPromluva;
        return promluvaUcitel;
    }
       

}
