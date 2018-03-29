/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.logika;

/**
 *  Třída Postava - obsahuje konstruktor a jednoduche metody pro jedntolive postavy.
 *  
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */

public class Postava {
    private String jmeno;
    
    
    /**
     * konstruktor
     */
    public Postava(String jmeno) {
        this.jmeno = jmeno;
           }  
    
           
    /**
     * Metoda vrací jméno postavy
     *
     * @return    jméno
     */
    public String getJmeno() {
        return jmeno;
    }
    
    
    /**
     * Metoda nastavuje jméno postavy
          */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }
    
    
}