/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.Skola.logika;

import java.util.Observable;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Martin Weisser
 * @version    LS 2016/2017
 */
public class HerniPlan extends Observable {
    private static final String NAZEV_VITEZNE_LOKACE = "vychod";
    
    private Lokace aktualniLokace;
    private Uloziste ledvinka;
    private Uloziste ruka;
    private int pruchod;
    public boolean hori;
    public boolean zapnuty;
    public boolean naplneny;
    public boolean bufetackaVenku;
    public boolean ucitelVenku;
    
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozLokaceHry();
        ledvinka = new Uloziste(2);
        ruka = new Uloziste(1);
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    public void zalozLokaceHry() {
        Lokace ucebna = new Lokace("ucebna","počítačová učebna, kde nastal skrat", 120.0, 50.0);
        Lokace chodba = new Lokace("chodba", "chodba", 120.0, 140.0);
        Lokace bufet = new Lokace("bufet","bufet, místo dobrot a laskomin", 50.0, 135.0);
        Lokace wc = new Lokace("wc", "wc - místo, kde voda je vždy zadarmo", 50.0, 180.0);
        Lokace vytah = new Lokace("vytah","pozor! výtah je v době požáru nebezpečný", 125.0, 240.0);
        Lokace schodiste = new Lokace("schodiste", "schodiště", 90.0, 240.0);
        Lokace sklad = new Lokace("sklad","sklad, místo různých předmětů", 30.0, 240.0);
        Lokace aula = new Lokace("aula","aula, srdce školy", 90.0, 300.0);
        Lokace vratnice = new Lokace("vratnice","vrátnice, tajemný koutek pana školníka", 35.0, 370.0);
        Lokace satna = new Lokace("satna","šatna - bunda sem, bunda tam", 180.0, 370.0);
        Lokace vychod = new Lokace(NAZEV_VITEZNE_LOKACE, "východ", 280.0, 370.0);
        
        Predmet kyblik = new Predmet("kyblik", "ideální pro přenos vody", true, false);
        sklad.vlozPredmet(kyblik);
        
        Predmet kohoutek = new Predmet("kohoutek", "zde si můžeš natočit vodu", false, false);
        wc.vlozPredmet(kohoutek);
        
        Predmet tycinka = new Predmet("tycinka", "doplní ti energii", true, true);
        bufet.vlozPredmet(tycinka);
        
        Predmet HUE = new Predmet("HUE", "hlavni uzaver elekriny - vypinani elektriny pro celou budovu", false, false);
        vratnice.vlozPredmet(HUE);
        
        Predmet desetikoruna = new Predmet("desetikoruna", "Na zemi lezici desetikoruna", true, false);
        chodba.vlozPredmet(desetikoruna);
        
        Predmet katedra = new Predmet("katedra", "katedra - kralovstvi ucitelu", false, false);
        ucebna.vlozPredmet(katedra);
        
        Predmet automat = new Predmet("automat", "automat, ale momentalne je mimo provoz", false, false);
        bufet.vlozPredmet(automat);
        
        Predmet klice = new Predmet("klice", "klice, ktere se mozna budou hodit", true, false);
        chodba.vlozPredmet(klice);
        
        Predmet boiler = new Predmet("boiler", "boiler pro ohrev vody", false, false);
        sklad.vlozPredmet(boiler);
        
        Predmet gauc = new Predmet("gauc", "pohodovy gauc", false, false);
        aula.vlozPredmet(gauc);
        
        Predmet nastenka = new Predmet("nastenka", "skolni nastenka", false, false);
        chodba.vlozPredmet(nastenka);
        
        Predmet palmicka = new Predmet("palmicka", "vzrostla palmicka", false, false);
        aula.vlozPredmet(palmicka);
        
        Predmet skrinky = new Predmet("skrinky", "skolni skrinky pro uschovani veci", false, false);
        satna.vlozPredmet(skrinky);
        
        Predmet zachod = new Predmet("zachod", "zachodova misa", false, false);
        wc.vlozPredmet(zachod);
        
        Predmet penezenka = new Predmet("penezenka", "prazdna penezenka s doklady", true, false);
        schodiste.vlozPredmet(penezenka);
                
        Postava ucitel = new Postava ("ucitel");
        ucebna.vlozPostavu(ucitel);
        
        Postava bufetacka = new Postava ("bufetacka");
        bufet.vlozPostavu(bufetacka);
                
           
        ucebna.setVychod(chodba);
        chodba.setVychod(ucebna);
        chodba.setVychod(bufet);
        chodba.setVychod(vytah);
        chodba.setVychod(schodiste);
        bufet.setVychod(wc);
        bufet.setVychod(chodba);
        wc.setVychod(bufet);
        vytah.setVychod(chodba);
        vytah.setVychod(aula);
        schodiste.setVychod(chodba);
        schodiste.setVychod(sklad);
        schodiste.setVychod(aula);
        sklad.setVychod(schodiste);
        aula.setVychod(vytah);
        aula.setVychod(schodiste);
        aula.setVychod(vratnice);
        aula.setVychod(satna);
        vratnice.setVychod(aula);
        satna.setVychod(aula);
        satna.setVychod(vychod);
        
        
        hori = true;
        zapnuty = true;
        bufetackaVenku = false;
        ucitelVenku = false;
        naplneny = false;
        

        aktualniLokace = ucebna;  
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       setChanged();
       notifyObservers();
    }
    
    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public boolean hracVyhral() {
        return aktualniLokace.getNazev().equals(NAZEV_VITEZNE_LOKACE);
    }
    
    /**
     * Metoda vrací, jestli hoří v budově
     */
    public boolean jestliHori()
    {
        return hori;
    }
    
    /**
     * Metoda vrací, jestli je vypnutý/zapnutý HUE
     */
    public boolean hua()
    {
        return zapnuty;
    }
    
    /**
     * Metoda vrací, jestli je bufetacka v budově
     */
    public boolean jestliBufetacka()
    {
        return bufetackaVenku;
    }
    
    /**
     * Metoda vrací, jestli je učitel v budově
     */
    public boolean jestliUcitel()
    {
        return ucitelVenku;
    }
    
    /**
     * Metoda vrací, jestli je kýbl naplněný vodou
     */
    public boolean kybl()
    {
        return naplneny;
    }   
    
    /**
     * Metoda vrací odkaz na ledvinku
     */
    public Uloziste getLedvinka()
    {
        return ledvinka;
    }
    
    /**
     * Metoda vrací odkaz na ruku
     */
    public Uloziste getRuka() 
    {
        return ruka;
    }
    
    /**
     * Metoda vrací počet průchodů mezi lokacemi
     */
    public int getPruchod()
    {
        return pruchod;
    }
    
    /**
     * Metoda zvyšuje průchod mezi lokacemi o 1 hodnotu
     */
    public void prechazeni()
    {
        pruchod++;
    }
    
    /**
     * Metoda nastavuje průchod na hodnotu 0
     */
    public void setPruchod()
    {
        pruchod = 0;
    }
    
    /**
     * Metoda nastavuje, že HUE je vypnuté
     */
    public void setHue()
    {
        zapnuty = false;
    }
    
    /**
     * Metoda nastavuje únik učitele
     */
    public void setUcitel()
    {
        ucitelVenku = true;
    }
    
    /**
     * Metoda nastavuje únik bufetacky
     */
    public void setBufetacka()
    {
        bufetackaVenku = true;
    }
    
    /**
     * Metoda nastavuje, že nehoři
     */
    public void setHori()
    {
        hori = false;
    }
    
    /**
     * Metoda nastavuje, že je naplněný kýbl
     */
    public void setNaplneny()
    {
        naplneny = true;
    }
}




