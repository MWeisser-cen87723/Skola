/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.martinweisser.uiText;

import java.io.*;
import java.util.Scanner;

import com.github.martinweisser.logika.IHra;

/**
 * Class TextoveRozhrani
 *
 * Toto je třída uživatelského rozhraní aplikace Adventura. Třída vytváří instanci třídy Hra,
 * která představuje logiku aplikace. Čte vstup zadaný uživatelem a předává tento řetězec logice
 * a vypisuje odpověď logiky na konzoli.
 *
 * @author     Martin Weisser, Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2016/2017
 */

public class TextoveRozhrani {
    private IHra hra;

    /**
     * Vytváří hru.
     */
    public TextoveRozhrani(IHra hra) {
        this.hra = hra;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry (dokud metoda konecHry() z logiky nevrátí
     * hodnotu true). Nakonec vypíše text epilogu.
     */
    public void hraj() throws InterruptedException {
        System.out.println(hra.vratUvitani());
        System.out.println(hra.vratUvod());
        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!hra.konecHry()) {
            String radek = prectiString();
            System.out.println(hra.zpracujPrikaz(radek));
        }

        System.out.println(hra.vratEpilog());
    }
    
    public void hrajZeSouboru(String nazevSouboru) throws InterruptedException {
        System.out.println("Nacitam prikazy ze souboru " + nazevSouboru + "\n");
        
        try(BufferedReader ctec = new BufferedReader(new FileReader(nazevSouboru))) {
            System.out.println(hra.vratUvitani());
            
            String radek = ctec.readLine();
            while(radek != null && hra.konecHry() != true) {
                System.out.println("* " + radek + " *");
                System.out.println(hra.zpracujPrikaz(radek));
                radek = ctec.readLine();
            }
            
            System.out.println(hra.vratEpilog());
        }
        catch(FileNotFoundException e) {
            System.out.println("Soubor " + nazevSouboru + " nelze precist");
        }
        catch(IOException e) {
            System.out.println("Doslo k neocekavane chybe");
        }
                                       
        System.out.println(hra.vratUvitani());
        

        System.out.println(hra.vratEpilog());
    }
    

    /**
     * Metoda přečte příkaz z příkazového řádku
     *
     * @return    vrací přečtený příkaz jako instanci třídy String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}
