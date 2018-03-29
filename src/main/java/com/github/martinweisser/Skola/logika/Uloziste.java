package com.github.martinweisser.Skola.logika;
import java.util.*;

/**
 *  Třída Uloziste
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class Uloziste
{
    public Set<Predmet> seznamPredmetu;   
    private int  MAX_VECI;
    

    /*********************
     *  Konstruktor třídy
     ********************/
    public Uloziste(int MAX_VECI)
    {
        seznamPredmetu = new HashSet<>();
        this.MAX_VECI = MAX_VECI;
    }

    /**
     * Metoda vkládá předmět do ruky
     *
     * @return    predmet
     */ 
    public Predmet setPredmet(Predmet p) 
    {
        seznamPredmetu.add(p);
        return p;
        }
        
     /**
     * Metoda hledá předmět na základě názvu
     *
     * @return    předmět
     */
    public Predmet getPredmet(String nazevPredmetu) {
        Predmet predmet = null;
        for (Predmet p : seznamPredmetu)
        {
            if(p.getNazev().equals(nazevPredmetu))
            {
                predmet = p;
                }       
        }        
        return predmet;
    }       
    

    /**
     * Metoda zjišťující počet předmětů v ruce
     *
     * @return    počet předmětů
     */
    public int pocetPredmetu () 
    {
        return seznamPredmetu.size();    
    }

    
    /**
     * Metoda zjišťující, zda se něco vejde do ruky
     *
     * @return   true/false
     */
    public boolean vejdeSePredmet() 
    {
        if (pocetPredmetu() < MAX_VECI) 
        {
            return true;
        }
        
        return false;       
    }

    /**
     * Metoda odebírá předmět z ruky
     */
    public Predmet zahodPredmet(String nazevPredmetu) {
        for(Predmet neco : seznamPredmetu) {
            if(neco.getNazev().equals(nazevPredmetu)) {
                seznamPredmetu.remove(neco);
                return neco;
            }
        }
        return null;
    }

    /**
     * Metoda se ptá, zda ruka obsahuje předmět
     * @return    true/false
     */   
    public boolean obsahujePredmet(String nazevPredmetu) {
        for(Predmet neco : seznamPredmetu) {            
            if(neco.getNazev().equals(nazevPredmetu)) {
                return true;
            } 
        }        
        return false;
    }
        

}


