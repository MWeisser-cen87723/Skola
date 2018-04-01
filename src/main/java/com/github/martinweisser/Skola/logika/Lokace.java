/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.Skola.logika;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;


/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 *
 * @author     Martin Weisser, Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    LS 2016/2017
 */
public class Lokace {

    private String nazev;
    private String popis;
    private Set<Lokace> vychody;   // obsahuje sousední lokace
    private Map<String, Predmet> predmety; // kazdy Predmet se da dohledat podle jmena
    private Map<String, Postava> postavy;
    public List <Postava> seznamPostav;
    //public String[] poleVychodu;
    

    /**
     * Vytvoření lokace se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param    nazev nazev lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    popis Popis lokace
     */
    public Lokace(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        predmety = new HashMap<>();
        postavy = new HashMap<>();
    }

    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    vedlejsi lokace, která sousedi s aktualní lokací.
     *
     */
    public void setVychod(Lokace vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Lokace)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }
        // přetypujeme parametr na typ Lokace 
        Lokace druha = (Lokace) o;


        return (java.util.Objects.equals(this.nazev, druha.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    název lokace
     */
    public String getNazev() {
        return nazev;       
    }
    

    /**
     * Vrací "dlouhý" popis lokace, který může vypadat následovně: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhý popis lokace
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti/lokaci " + popis + ".\n"
                + popisVychodu() + ".\n"
                + seznamPredmetu() + " \n"
                + seznamPostav() + " \n";
    }
    
    public String seznamPredmetu() {
        String seznam = "predmety: ";
        for(String nazevPredmetu : predmety.keySet()) {
            seznam += " " + nazevPredmetu;
        }
        return seznam;
    }
    
    public String seznamPostav(){
        String seznam = "postavy: ";
        for(String jmenoPostavy : postavy.keySet()) {
            seznam += " " + jmenoPostavy;
        }
        return seznam;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    private String popisVychodu() {
        String vracenyText = "vychody: ";
        for (Lokace sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     nazevSouseda Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Lokace vratSousedniLokaci(String nazevSouseda) {
        List<Object>hledaneLokace = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return (Lokace) hledaneLokace.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Lokace> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    
    /**
     * Každý východ dostupný v aktuální lokaci přidá do pole.
     *
     * @return    pole dostupných východů v aktuální lokaci
     */
    public ArrayList<String> seznamVychodu() {
    		ArrayList<String> poleVychodu = new ArrayList<String>();
    		for (Lokace sousedni : vychody) {
               poleVychodu.add(sousedni.getNazev());
            }
    		return poleVychodu;
	}
		
	
    
    /**
     * Metoda vkládá předmět do lokace
     */
    public void vlozPredmet(Predmet predmet){
        predmety.put(predmet.getNazev(), predmet);   
    }
    
    /**
     * Metoda odebírá předmět z lokace
     */
    public Predmet vezmiPredmet(String nazevPredmetu) {
        return predmety.remove(nazevPredmetu);
    }
    
    /**
     * Metoda hledá předmět v lokace
     * 
     * @return predmet
     */
    public Predmet najdiPredmet(String nazevPredmetu) {
        return predmety.get(nazevPredmetu);
    }
    
    /**
     * Metoda se ptá, zda lokace obsahuje predmet
     *
     * @return    predmet
     */
    public boolean obsahujePredmet(String nazevPredmetu) {
        return predmety.containsKey(nazevPredmetu);
    }
    
    /**
     * Metoda vkládá postavu do lokace
     */
    public void vlozPostavu(Postava postava){
        postavy.put(postava.getJmeno(), postava);
    }
    
    /**
     * Metoda hledá postavu v lokace
     *
     * @return    ppostava
     */
    public Postava najdiPostavu(String jmenoPostavy) {
        return postavy.get(jmenoPostavy);
    }
    
    /**
     * Metoda se ptá, zda je postava v lokaci
     *
     * @return    postava
     */
    public boolean obsahujePostavu(String jmenoPostavy) {
        return postavy.containsKey(jmenoPostavy);
    }
    
    /**
     * Metoda odebírá postavu z lokace
     *
     * @return    postava
     */
    public Postava zahodPostavu(String jmenoPostavy){
        return postavy.remove(jmenoPostavy);
        
    }
    
       
}
