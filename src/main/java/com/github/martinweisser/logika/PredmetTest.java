/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PredmetTest slouží ke komplexnímu otestování třídy Predmet 
 *
 * @author    Martin Weisser
 * @version   1.0
 */
public class PredmetTest
{
    
    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    /***************************************************************************
     * Testuje vytvoření předmětů a nastavení různých vlastností.
     * Zkontroluje se, zda se změnily vlastnosti jen u předmětu,
     * kterému byly nastavovány jiné vlastnosti.
     * 
     */
    @Test
    public void testPredmetu() {
        Predmet predmet1 = new Predmet("predmet1", "predmet1", true, true);
        Predmet predmet2 = new Predmet("predmet2", "predmet2", false, false);
        assertEquals(true, predmet1.isPrenositelny());
        assertEquals(true, predmet1.isJedly());
        assertEquals(false, predmet2.isPrenositelny());
        assertEquals(false, predmet2.isJedly());
        predmet1.setPrenositelny(false);
        predmet1.setJedly(false);
        predmet1.setPopis("predmet3");
        assertEquals(false, predmet1.isPrenositelny());
        assertEquals(false, predmet1.isJedly());
        assertEquals("predmet3", predmet1.getPopis());
        assertEquals(false, predmet2.isPrenositelny());
        assertEquals(false, predmet2.isJedly());
    }
}
