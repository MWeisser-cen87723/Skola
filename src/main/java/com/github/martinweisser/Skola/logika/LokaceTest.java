package com.github.martinweisser.Skola.logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování
 * třídy Lokace
 *
 * @author     Martin Weisser
 * @version    LS 2016/2017
 */
public class LokaceTest
{
    
    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Lokace lokace1 = new Lokace("hala", "vstupní hala budovy VŠE na Jižním městě");
        Lokace lokace2 = new Lokace("bufet", "bufet, kam si můžete zajít na svačinku");
        lokace1.setVychod(lokace2);
        lokace2.setVychod(lokace1); 
        assertEquals(lokace2, lokace1.vratSousedniLokaci("bufet"));
        assertEquals(null, lokace2.vratSousedniLokaci("pokoj"));
    }

}
