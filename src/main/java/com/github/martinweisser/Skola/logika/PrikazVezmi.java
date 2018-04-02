package com.github.martinweisser.Skola.logika;


/**
 *  Třída PrikazVezmi - obsahuje příkaz pro sebrání předmětů v lokaci
 *  a jejich uložení do ledvinky nebo uchopení do ruky.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class PrikazVezmi implements IPrikaz
{
    private static final String NAZEV = "vezmi";
    private HerniPlan hPlan;
    
    
    
    /**
     * Konstruktor
     */
    
    public PrikazVezmi(HerniPlan hPlan) {
        this.hPlan = hPlan;
                }
    
    /**
     * Metoda pro provedení příkazu - sebrání předmětu do ledvinky nebo do ruky.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté zda je předmět v aktuální lokaci.
     * Následně na základě určitých podmínek - zda je to určené do ledvinky ne o do ruky - 
     * provádí sebrání.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    @Override
    public String proved(String... parametry) {
        if(parametry.length < 1) {
           return "Nevim, co sebrat";
        }
        
        if(parametry.length > 1) {
           return "Neumim sebrat vic predmetu najednou";
        }
        
        String nazevPredmetu = parametry[0];
               
        Lokace aktLokace = hPlan.getAktualniLokace();
        aktLokace.obsahujePredmet(nazevPredmetu);
        
        if(!aktLokace.obsahujePredmet(nazevPredmetu)) {
            return "Predmet " + nazevPredmetu + " tady neni";
        }
        
        Predmet p = aktLokace.vezmiPredmet(nazevPredmetu);
       
        if(!p.isPrenositelny()) {
            aktLokace.vlozPredmet(p);
            return "Predmet " + nazevPredmetu + " fakt neuneses";
        }
        
        if(p.isJedly()) {
            aktLokace.vlozPredmet(p);
            return "Predmet " + nazevPredmetu + " je urceny k snezeni. Misto sebrani bys ho mel snist!";
        }
        
        
        Uloziste ruka = hPlan.getRuka();
        Uloziste ledvinka = hPlan.getLedvinka();
        
        
        if(p.getNazev().equals("kyblik")){
            ruka.setPredmet(p);
            return "Uchopil jsi kybl do ruky!";
        }
        
        if(!ledvinka.vejdeSePredmet()) {
            aktLokace.vlozPredmet(p);
            return "V ledvince uz nemas volne misto, musis neco vyhodit!";
        }else {
            ledvinka.setPredmet(p);
            return "Sebral jsi predmet " + nazevPredmetu;
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
