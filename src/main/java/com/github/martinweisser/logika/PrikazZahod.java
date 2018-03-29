package com.github.martinweisser.logika;


/**
 *  Třída PrikazZahod - obsahuje příkaz pro zahození předmětu z ledviny nebo ruky
 *  a jeho zanechání v aktuální lokace.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class PrikazZahod implements IPrikaz
{
    private static final String NAZEV = "zahod";
    private HerniPlan hPlan;
            
    /*********************
     *  Konstruktor třídy
     ********************/
    public PrikazZahod(HerniPlan hPlan) {
        this.hPlan = hPlan;
    }
    
    /**
     * Metoda pro provedení příkazu - zahození předmětu z ledvinky nebo z ruky.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté zda je předmět v aktuální lokaci, respektive u osoby.
     * Následně zahodí předmět.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    public String proved(String... parametry) {
        
        if(parametry.length < 1) {
           return "Nevim, co zahodit";
        }
        
        if(parametry.length > 1) {
           return "Neumim zahodit vic predmetu najednou";
        }
        
        String nazevPredmetu = parametry[0];
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        aktLokace.obsahujePredmet(nazevPredmetu);
        
        Predmet p1 = aktLokace.vezmiPredmet(nazevPredmetu);
                
        Uloziste ruka = hPlan.getRuka();
        Uloziste ledvinka = hPlan.getLedvinka();
        
                              
        if(ledvinka.obsahujePredmet(nazevPredmetu)) {
            Predmet p2 = ledvinka.getPredmet(nazevPredmetu);
            
            aktLokace.vlozPredmet(p2);
            ledvinka.zahodPredmet(nazevPredmetu);
            return "Zahodil jsi predmet: " + nazevPredmetu + ".";
        }
        
        if(ruka.obsahujePredmet(nazevPredmetu)) {
            Predmet p2 = ruka.getPredmet(nazevPredmetu);
            
            aktLokace.vlozPredmet(p2);
            ruka.zahodPredmet(nazevPredmetu);
            return "Zahodil jsi predmet: " + nazevPredmetu + ".";
        }
        else {
            return "Nelze zahodit " + nazevPredmetu + ". Tento předmět nemáš.";
        }
    
    }
    
    /**
     * Metoda vrací název příkazu, který můžeme zadat na konzoli
     *
     * @return    nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }
    
}