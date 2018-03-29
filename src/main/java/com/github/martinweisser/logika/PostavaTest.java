/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PostavaTest slouží ke komplexnímu otestování třídy Postava
 *
 * @author    Martin Weisser
 * @version   1.0
 */
public class PostavaTest
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

    /**
     * Testuje, zda jde vytvořit postava
     */
    @Test
    public  void testPostava() {		
        Postava postava1 = new Postava("postava1");
        Postava postava2 = new Postava("postava2");
        assertEquals("postava1", postava1.getJmeno());
        assertEquals("postava2", postava2.getJmeno());
                
    }
}
