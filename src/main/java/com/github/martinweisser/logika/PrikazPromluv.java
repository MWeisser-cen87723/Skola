package com.github.martinweisser.logika;


/**
 *  Třída PrikazPromluv - obsahuje příkaz pro promlouvání s osobami
 *  v jednotlivých lokacích.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */
public class PrikazPromluv implements IPrikaz 
{
    private static final String NAZEV = "promluv";
    private HerniPlan hPlan;
    
    
    public PrikazPromluv(HerniPlan hPlan) {
        this.hPlan = hPlan;
        
    }
    
    /**
     * Metoda pro provedení příkazu - promluvení s osobou.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté zda je osoba v aktuální lokaci.
     * Následně vypíše promlouvání požadované osoby s hlavní postavou.
     *
     * @param parametry pole parametrů na příkazové řádce.
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */
    public String proved(String... parametry) {
        
        if(parametry.length < 1) {
           return "Nevim s kym mam promluvit!";
        }
        
        if(parametry.length > 1) {
           return "Neumim promluvit s vice lidmi!";
        }
        
        String jmenoPostavy = parametry[0];
              
        Lokace aktLokace = hPlan.getAktualniLokace();
        aktLokace.obsahujePostavu(jmenoPostavy);
        if(!aktLokace.obsahujePostavu(jmenoPostavy)) {
            return "Osoba " + jmenoPostavy + " tady neni";
        }
                
        Promluva promluva = new Promluva();
        
        if(jmenoPostavy.equals("ucitel"))
        {
            promluva.vratPromluvuUcitel();
            aktLokace.zahodPostavu(jmenoPostavy);
            
            return "Premluvil jsi ucitele k opusteni budovy!";
        }
        
        if(jmenoPostavy.equals("bufetacka"))
        {
            promluva.vratPromluvuBufetacka();
            aktLokace.zahodPostavu(jmenoPostavy);
            return "Premluvil jsi bufetacku k opusteni budovy!";
        }
        
        return "Nepodarilo se promluvit!";
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
