/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.Skola.logika;
import java.util.Random;
/**
 *  Třída PrikazJdi - obsahuje příkaz pro procházení
 *  mezi dvěmi lokacemi.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Hra hra;
    private Lokace lokace;

   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.hra = hra;
        this.lokace = lokace;
    }

    /**
     * Metoda pro provedení příkazu - přecházení do jiné lokace.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté zda požadovaná lokace je sousední lokací.
     * Poté provede přechod do sousední lokace.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parametry[0];
        String mira = "\nMira vycerpani: ";

        Lokace sousedniLokace = (Lokace) plan.getAktualniLokace().vratSousedniLokaci(smer);

        if (sousedniLokace == null) {
            return "Tam se odsud jít nedá!";
        }
        
                
        if (sousedniLokace.getNazev().equals("vytah") && plan.hua())
        {
            Random cislo = new Random();
            int  n = cislo.nextInt(2) + 1;
            
            if (n == 1) {
                hra.setKonecHry(true);
                return "Vytah se urval! Zahynul jsi!\n";
                }
            
            if (n == 2) {
                plan.prechazeni();
                plan.setAktualniLokace(sousedniLokace);
                return sousedniLokace.dlouhyPopis() + mira + plan.getPruchod();
            }
           
        }
        
        if (sousedniLokace.getNazev().equals("vytah") && !plan.hua())
        {
            return "Nemuzes pouzit vytah! Vyplnul jsi elektrinu!";
        }
        
        if (plan.getPruchod() == 11)
        {
            hra.setKonecHry(true);
            return "Zemrel jsi vycerpanim!\n";
            }
        
        if (plan.getPruchod() == 8)
        {
            System.out.println("\nPOZOR! Mel by jsi si hlidat svoje vycerpani!");
            plan.prechazeni();
            plan.setAktualniLokace(sousedniLokace);
            return sousedniLokace.dlouhyPopis() + mira + plan.getPruchod();
        }
        
        if (plan.getPruchod() == 9)
        {
            System.out.println("\nPOZOR! Jsi skoro vycerpany!");
            plan.prechazeni();
            plan.setAktualniLokace(sousedniLokace);
            return sousedniLokace.dlouhyPopis() + mira + plan.getPruchod();
            
        }
        
        if(sousedniLokace.getNazev().equals("satna"))
        {
            if(plan.jestliHori() || plan.jestliBufetacka() || plan.jestliUcitel()) {
            		return "Jsi si jisty, ze v budouve uz nejsou zadne osoby, hlavni uzaver elektriny je vypnuty a ohen uhasen?";
           }
             else {
            	 	 plan.prechazeni();
                 plan.setAktualniLokace(sousedniLokace);
                 return sousedniLokace.dlouhyPopis() + mira + plan.getPruchod();
           }}
        
        if(sousedniLokace.getNazev().equals("vychod")) {
        		plan.setKonec(true);
    			return hra.vratEpilog();
        }
               
        else {
            plan.prechazeni();
            plan.setAktualniLokace(sousedniLokace);
            return sousedniLokace.dlouhyPopis() + mira + plan.getPruchod();
        }
    }
    

    /**
     * Metoda vrací název příkazu, který můžeme zadat na konzoli
     *
     * @return    nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
