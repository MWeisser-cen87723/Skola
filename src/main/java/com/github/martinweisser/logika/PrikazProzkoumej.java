package com.github.martinweisser.logika;



/**
 *  Třída PrikazProzkoumej - obsahuje příkaz pro prozkoumání předmětu
 *  v dané lokaci.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */

public class PrikazProzkoumej implements IPrikaz
{
    private static final String NAZEV = "prozkoumej";
    
    private Predmet predmet;
    private HerniPlan hPlan;
        
    /*********************
     *  Konstruktor třídy
     ********************/
    public PrikazProzkoumej(HerniPlan hPlan) {
        this.hPlan = hPlan;
    }
    
    /*********************
     *  Konstruktor třídy
     ********************/
    public PrikazProzkoumej(Predmet predmet) {
        this.predmet = predmet;
    }
    
    /**
     * Metoda pro provedení příkazu - prozkoumání aktuální lokace nebo předmětu.
     * Nejdříve zkontroluje validního zadání příkazu.
     * Poté na základě zadání parametry se rozhodne,
     * zda se prozkoumává lokace nebo předmět v lokaci.
     * Následně vypíše požadovaný popis.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    
    public String proved(String... parametry) {
        
        if(parametry.length > 1) {
            return "Nechapu co po mne chces!";
        }
        
        if(parametry.length > 0) {
            String nazevPredmetu = parametry[0];
            Lokace aktLokace = hPlan.getAktualniLokace();
            
            if (aktLokace.obsahujePredmet(nazevPredmetu)) {
                return aktLokace.najdiPredmet(nazevPredmetu).getPopis();
                }
            
            Uloziste ruka = hPlan.getRuka();
            Uloziste ledvinka = hPlan.getLedvinka();           
            if(ledvinka.obsahujePredmet(nazevPredmetu)) {
                return "Predmet uz mas v ledvince";
            }
            
            if(ruka.obsahujePredmet(nazevPredmetu)) {
                return "Predmet uz mas v ruce";
            }
            
            return "Predmet " + nazevPredmetu + " tady neni";            
        } else {
            Lokace aktLokace = hPlan.getAktualniLokace();
            return aktLokace.dlouhyPopis();
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
