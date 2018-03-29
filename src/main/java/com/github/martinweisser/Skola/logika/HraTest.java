package com.github.martinweisser.Skola.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author     Jarmila Pavlíčková, Jan Říha, Martin Weisser
 * @version    LS 2016/2017
 */

public class HraTest {
    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() throws InterruptedException {
        assertEquals("ucebna", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePostavu("ucitel"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("katedra"));
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("klice"));
        hra1.zpracujPrikaz("vezmi desetikoruna");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, hra1.getHerniPlan().getLedvinka().obsahujePredmet("desetikoruna"));
        hra1.zpracujPrikaz("jdi bufet");
        assertEquals(false, hra1.konecHry());
        assertEquals("bufet", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePostavu("bufetacka"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("tycinka"));
        assertEquals(true, hra1.getHerniPlan().getLedvinka().obsahujePredmet("desetikoruna"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("automat"));
        hra1.zpracujPrikaz("jdi wc");
        assertEquals(false, hra1.konecHry());
        assertEquals("wc", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("kohoutek"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("zachod"));
        hra1.zpracujPrikaz("jdi bufet");
        assertEquals(false, hra1.konecHry());
        assertEquals("bufet", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("pouzij tycinka");
        assertEquals(false, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("tycinka"));
        assertEquals(false, hra1.getHerniPlan().getLedvinka().obsahujePredmet("desetikoruna"));
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals(false, hra1.konecHry());
        assertEquals("chodba", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(false, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("desetikoruna"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("klice"));
        hra1.zpracujPrikaz("jdi schodiste");
        assertEquals(false, hra1.konecHry());
        assertEquals("schodiste", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("penezenka"));
        hra1.zpracujPrikaz("jdi sklad");
        assertEquals(false, hra1.konecHry());
        assertEquals("sklad", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("kyblik"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("boiler"));
        hra1.zpracujPrikaz("jdi schodiste");
        assertEquals(false, hra1.konecHry());
        assertEquals("schodiste", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("penezenka"));
        hra1.zpracujPrikaz("jdi aula");
        assertEquals(false, hra1.konecHry());
        assertEquals("aula", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("gauc"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("palmicka"));
        hra1.zpracujPrikaz("jdi vratnice");
        assertEquals(false, hra1.konecHry());
        assertEquals("vratnice", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("HUE"));
        hra1.zpracujPrikaz("jdi aula");
        assertEquals(false, hra1.konecHry());
        assertEquals("aula", hra1.getHerniPlan().getAktualniLokace().getNazev());
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("gauc"));
        assertEquals(true, hra1.getHerniPlan().getAktualniLokace().obsahujePredmet("palmicka"));
        hra1.zpracujPrikaz("jdi satna");
        assertEquals(false, hra1.konecHry());
        assertEquals("aula", hra1.getHerniPlan().getAktualniLokace().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
}

