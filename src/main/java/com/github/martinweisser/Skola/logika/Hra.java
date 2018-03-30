/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.Skola.logika;


/**
 * Třída Hra - třída představující logiku adventury.
 * 
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy HerniPlan, která inicializuje
 * mistnosti hry a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author     Martin Weisser, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    
    private HerniPlan herniPlan;
    public boolean konecHry = false;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazVezmi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(herniPlan));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
         //return "HRA ZACHRAŇ ŠKOLU" +  "\n";
         return "\n" + herniPlan.getAktualniLokace().dlouhyPopis() + "\n" ; //dočasné řešení
    }
    
    public String vratUvod() {
        String uvod = "\n" + "Ach né! V počítačové účebně nastal skrat a začlo hořet!\n" +
                      "Dokážeš uhasit oheň a zachránit všechny osoby v budově?" + "\n" +
                      "Ukaž, co v tobě je!" + "\n" +
                      "Držím palce :)" + "\n" ;
                      
        try {
            char[] pismena = uvod.toCharArray();
            for (int i = 0; i < pismena.length; i++) {
                    System.out.print(pismena[i]);
                    Thread.sleep(100);}
                }
        catch(InterruptedException ex){
             Thread.currentThread().interrupt();
        }
         return null;
        //return "\n" + herniPlan.getAktualniLokace().dlouhyPopis() + "\n" ;
    }
       
    /**
     * Vrátí závěrečnou zprávu pro hráče. -- výhra/prohra
     */
    public String vratEpilog() {
        if(herniPlan.hracVyhral()) {
            return "\nJsi BOREC! Vyhral jsi! Zachranil jsi skolu!"
                    +  "\nDiky za vybornou hru! AHOJ!"
                    +  "\n"
                    +  "\nMW STUDIO, 2017";
        }
        
        return "Dík, že jste si zahráli.  Ahoj.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param    radek  text, který zadal uživatel jako příkaz do hry.
     * @return   vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek)  {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" ... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            
            if(herniPlan.hracVyhral()) {
                konecHry = true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *  
     * @param    konecHry hodnota false = konec hry, true = hra pokračuje
     */
    public void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
        konecHry = true;
    }
    
    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     * @return    odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

