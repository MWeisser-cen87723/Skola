package com.github.martinweisser.Skola.logika;



/**
 *  Třída Predmet - obsahuje příkazy pro vytváření a nastavování proměnných
 *  jednotlivých předmětů.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */

public class Predmet {
    private String nazev;
    private String popis;
    private boolean prenositelny;
    private boolean jedly;
       
    /********
     * konstruktor
     */
    public Predmet(String nazev, String popis, boolean prenositelny, boolean jedly) {
        //muzu doplnit kolik atributu chci -- jaky se boudou hodit
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelny = prenositelny;
        this.jedly = jedly;
                    }
    
    /**
     * Metoda vrací název předmětu
     *
     * @return    nazev
     */               
    public String getNazev() {
        return nazev;
    }
    
    /**
     * Metoda vrací popis predmetu
     *
     * @return    popis
     */
    public String getPopis() {
        return popis;
    }
    
    /**
     * Metoda vraci přenositelnost
     *
     * @return    prenositelnost
     */
    public boolean isPrenositelny() {
        return prenositelny;
    }
    
    /**
     * Metoda vraci jedlost předmetu
     *
     * @return    jedlost
     */
    public boolean isJedly() {
        return jedly;
    }
    
    /**
     * Metoda nastavuje popis
     */
    public void setPopis(String popis) {
        this.popis = popis;
    }
    
    /**
     * Metoda nastavuje název
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    
    /**
     * Metoda nastavuje prenositelnost
     */
    public void setPrenositelny(boolean prenositelny) {
        this.prenositelny = prenositelny;
    }
    
    /**
     * Metoda nastavuje jedlost
     */
    public void setJedly(boolean jedly) {
        this.jedly = jedly;
    }
    
}
