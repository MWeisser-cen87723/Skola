package com.github.martinweisser.Skola.ui;

import com.github.martinweisser.Skola.logika.IHra;
//import com.github.martinweisser.Skola.logika.Lokace;
import com.github.martinweisser.Skola.logika.SeznamPrikazu;

import java.util.Observable;
import java.util.Observer;
//import java.util.Vector;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
//import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;


/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * @param <Veci>
 * @param <Postavy>
 * @param <Prikazy>
 * @param <Vychody>
 * 
 * @authors Filip Vencovsky, Martin Weisser
 *
 */
public class HomeController < Veci, Postavy, Prikazy, Vychody > extends GridPane implements Observer {

    @FXML private TextField vstupniText;
    @FXML private TextArea vystup;
    @FXML private ListView < Veci > mistnost;
    @FXML private ListView < Prikazy > prikazy;
    @FXML private TextArea vycerpani;
    @FXML private ListView < Vychody > vychody;
    @FXML private ImageView student;
    @FXML private ImageView klice;
    @FXML private ImageView penezenka;
    @FXML private ImageView desetikoruna;
    @FXML private ImageView kyblik;
    @FXML private ImageView bufetackaIm;
    @FXML private ImageView ucitelIm;

    private IHra hra;

    private String ucitel;
    private String bufetacka;

    //private SeznamPrikazu seznampr;



    /**
     * metoda čte příkaz ze vstupního textového pole
     * a zpracuje ho
     */
    @FXML public void odesliPrikaz() {
        String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
        vystup.appendText("\n----------\n" + vstupniText.getText() + "\n----------\n");
        vystup.appendText(vystupPrikazu);
        vstupniText.setText("");

        /*if(hra.konecHry()) {
        	vystup.appendText("\n\n Konec hry \n");
        	vstupniText.setEditable(false);
        }*/

    }

    /**
     * metoda vypíše zvolenou hodntu
     * do příkazového pole
     */
    @FXML public void zvolenVychod() {
        vstupniText.appendText(vychody.getSelectionModel().toString());
    }


    /**
     * metoda napíše příkaz konec do příkazového řádku
     * a zavolá matedu na zpracování příkazu
     */
    /*@FXML public void konecHry() {
    	vstupniText.setText("konec");
    	odesliPrikaz();
    	vstupniText.setEditable(false);
    }*/

    @FXML public void konecHry() {
        hra.setKonecHry(true);
        vystup.appendText("\n\n Konec hry \n");
        vstupniText.setEditable(false);
    }

    /**
     * metoda napíše příkaz začátek do příkazového řádku
     * a zavolá matedu na zpracování příkazu
     */
    @FXML public void zacatekHry() {
        hra.setKonecHry(false);
        hra.getHerniPlan().zalozLokaceHry();
        hra.getHerniPlan().setPruchod();
        vstupniText.setEditable(true);
        vystup.setText(hra.vratUvitani());
        inicializuj(hra);
    }

    /**
     * metoda vypíše do textArea vycerpani
     * míru vyčerpání
     */
    public void vycerpani(IHra hra) {
        this.hra = hra;
        vycerpani.clear();
        vycerpani.setEditable(false);
        if (hra.getHerniPlan().getPruchod() < 5) {
            vycerpani.setText("Míra vyčerpání: " + hra.getHerniPlan().getPruchod());
        } else {
            vycerpani.setText("Míra vyčerpání: " + hra.getHerniPlan().getPruchod() + "\n" + "Měl by jsi doplnit energii!");
        }
    }


    /*
    public void noveOkno() {
    	Stage dialog = new Stage();
    	dialog.initModality(Modality.APPLICATION_MODAL);
    	
	
    }
    */

    /**
     * Metoda bude soužit pro předání objektu se spuštěnou hrou
     * kontroleru a zobrazí stav hry v grafice.
     * @param objekt spuštěné hry
     */
    public void inicializuj(IHra hra) {
        klice.setVisible(false);
        penezenka.setVisible(false);
        kyblik.setVisible(false);
        desetikoruna.setVisible(false);
        bufetackaIm.setVisible(false);
        ucitelIm.setVisible(true);
        vystup.clear();
        vystup.setText(hra.vratUvitani());
        //vystup.appendText(hra.vratUvod());
        vystup.setEditable(false);
        this.hra = hra;
        hra.getHerniPlan().addObserver(this);
        vstupniText.setText("");
        student.setX(hra.getHerniPlan().getAktualniLokace().getX());
        student.setY(hra.getHerniPlan().getAktualniLokace().getY());
        update(null, hra);

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        mistnost.getItems().clear();
        mistnost.getItems().add((Veci) hra.getHerniPlan().getAktualniLokace().seznamPredmetu());
        //prikazy.getItems().add((Prikazy) hra.getSeznamPrikazu());
        vycerpani(hra);
        vychody.getItems().clear();
        vychody.getItems().add((Vychody) hra.getHerniPlan().getAktualniLokace().getVychodyNazev());
        student.setX(hra.getHerniPlan().getAktualniLokace().getX());
        student.setY(hra.getHerniPlan().getAktualniLokace().getY());
        updatePostavy();
        updatePredmety();
    }


    /**
     * Metoda slouží k zobrazení obrázku předmětu
     * v případě jeho přítomnosti v ledvince/ruce
     */
    public void updatePredmety() {

        if (hra.getHerniPlan().getLedvinka().obsahujePredmet("klice")) {
            klice.setVisible(true);
        } else if (hra.getHerniPlan().getLedvinka().obsahujePredmet("penezenka")) {
            penezenka.setVisible(true);
        } else if (hra.getHerniPlan().getLedvinka().obsahujePredmet("desetikoruna")) {
            desetikoruna.setVisible(true);
        } else if (hra.getHerniPlan().getRuka().obsahujePredmet("kyblik")) {
            kyblik.setVisible(true);
        }
    }


    /**
     * Metoda slouží k zobrazení obrázku postavy
     * v případě její přítomnosti v lokaci
     */
    public void updatePostavy() {

        if (hra.getHerniPlan().getAktualniLokace().obsahujePostavu(bufetacka)) {
            bufetackaIm.setVisible(true);
        } else if (hra.getHerniPlan().getAktualniLokace().obsahujePostavu(ucitel)) {
            ucitelIm.setVisible(true);
        }
    }
}