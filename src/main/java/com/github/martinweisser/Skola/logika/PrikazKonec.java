/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.Skola.logika;

/**
 *  Třída PrikazKonec - obsahuje příkaz pro ukončení hry.
 *  
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Martin Weisser
 *@version    1.0
 *@created    2017-05-15
 */

class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param    hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * Metoda pro provedení příkazu - ukončení hry.
     * Nejdříve zkontroluje validního zadání příkazu
     * a poté ukončí hru
     *
     * @result výsledek zpracování - text, který se vypíše na konzoli
     */

    @Override
    public String proved(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
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
