package com.github.martinweisser.Skola.ui;

import com.github.martinweisser.Skola.logika.IHra;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * @param <Veci>
 * 
 * @authors Filip Vencovsky, Martin Weisser
 *
 */
public class HomeController < Veci > extends GridPane implements Observer {

    @FXML private TextField vstupniText;
    @FXML private TextArea vystup;
    @FXML private ListView < Veci > mistnost;
    @FXML private TextArea vycerpani;
    @FXML private ImageView student;
    @FXML private ImageView klice;
    @FXML private ImageView penezenka;
    @FXML private ImageView desetikoruna;
    @FXML private ImageView kyblik;
    @FXML private ImageView bufetackaIm;
    @FXML private ImageView ucitelIm;

    private IHra hra;

    /**
     * metoda čte příkaz ze vstupního textového pole
     * a zpracuje ho
     */
    @FXML public void odesliPrikaz() {
        String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
        vystup.appendText("\n\n> Zadal jsi příkaz: " + vstupniText.getText() + "\n ");
        vystup.appendText(vystupPrikazu);
        vstupniText.setText("");

    }

    /**
     * metoda napíše příkaz vezmi do textového pole
     */
    @FXML public void prikazVezmi() {
        vstupniText.setText("vezmi ");
    }

    /**
     * metoda napíše příkaz jdi do textového pole
     */
    @FXML public void prikazJdi() {
        vstupniText.setText("jdi ");
    }

    /**
     * metoda napíše příkaz použij do textového pole
     */
    @FXML public void prikazPouzij() {
        vstupniText.setText("pouzij ");
    }

    /**
     * metoda napíše příkaz napoveda do textového pole
     */
    @FXML public void prikazNapoveda() {
        vstupniText.setText("napoveda");
    }

    /**
     * metoda napíše příkaz promluv do textového pole
     */
    @FXML public void prikazPromluv() {
        vstupniText.setText("promluv");
    }

    /**
     * metoda napíše příkaz konec do textového pole
     */
    @FXML public void prikazKonec() {
        vstupniText.setText("konec");
    }

    /**
     * metoda napíše příkaz zahod do textového pole
     */
    @FXML public void prikazZahod() {
        vstupniText.setText("zahod ");
    }

    /**
     * metoda napíše příkaz prozkoumej do textového pole
     */
    @FXML public void prikazProzkoumej() {
        vstupniText.setText("prozkoumej");
    }

    /**
     * metoda napíše příkaz konec do příkazového řádku
     * a zavolá matedu na zpracování příkazu
     */
    @FXML public void konecHry() {
        hra.setKonecHry(true);
        vystup.appendText("\n\nUkončil jsi hru!\nNovou hru můžeš začít přes Hra > Nová hra");
        vstupniText.setEditable(false);
    }

    /**
     * metoda napíše příkaz začátek do příkazového řádku
     * a zavolá matedu na zpracování příkazu
     */
    @FXML public void zacatekHry() {
        hra.setKonecHry(false);
        hra.getHerniPlan().getLedvinka().seznamPredmetu.clear();
        hra.getHerniPlan().getRuka().seznamPredmetu.clear();
        hra.getHerniPlan().zalozLokaceHry();
        hra.getHerniPlan().setPruchod();

        vstupniText.setEditable(true);
        vystup.setText(hra.vratUvitani());

        inicializuj(hra);
    }

    /**
     * metoda aktualizuje herní reality
     * zavoláním metody update(null, hra);
     */
    @FXML public void update() {
        update(null, hra);
    }

    /**
     * metoda otevře stránky předmětu
     * java.vse.cz
     */
    @FXML public void strankyPredmetu() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("http://java.vse.cz"));
        } catch (IOException | URISyntaxException e2) {
            e2.printStackTrace();
        }
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

    /**
     * metoda otevře nové okno s informací o autorovi
     */
    @FXML public void about() {
        Label about = new Label("Hra zachraň školu! \nTextová verze: (c) MW 4.6.2017 \nGrafická verze: (c) MW 2.4.2018");

        StackPane newScene = new StackPane();
        newScene.getChildren().add(about);
        Scene scene = new Scene(newScene, 300, 200);
        Stage newWindow = new Stage();

        newWindow.setTitle("O vývojáři");
        newWindow.setScene(scene);
        newWindow.setAlwaysOnTop(true);
        newWindow.centerOnScreen();
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.setX(500);
        newWindow.setY(300);

        newWindow.show();
    }
    /**
     * metoda otevře soubor napoveda.html
     */
    @FXML public void napoveda() {
        Stage stage = new Stage();
        stage.setTitle("Nápověda");

        WebView webView = new WebView();
        webView.getEngine().load(com.github.martinweisser.Skola.main.Start.class.getResource("/napoveda.html").toExternalForm());

        stage.setScene(new Scene(webView, 500, 500));
        stage.show();
    }


    /**
     * Metoda bude soužit pro předání objektu se spuštěnou hrou
     * kontroleru a zobrazí stav hry v grafice.
     * @param objekt spuštěné hry
     */
    public void inicializuj(IHra hra) {
        vystup.clear();
        vystup.setText(hra.vratUvitani());
        vystup.setEditable(false);

        this.hra = hra;
        hra.getHerniPlan().addObserver(this);

        vstupniText.setText("");

        student.setX(hra.getHerniPlan().getAktualniLokace().getX());
        student.setY(hra.getHerniPlan().getAktualniLokace().getY());

        update(null, hra);

    }
    /**
     * Metoda slouží k aktualizaci herních
     * realit (pozice, predmety, postavy)
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        mistnost.getItems().clear();
        mistnost.getItems().add((Veci) hra.getHerniPlan().getAktualniLokace().seznamPredmetu());

        vycerpani(hra);

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
        } else {
            klice.setVisible(false);
        }

        if (hra.getHerniPlan().getLedvinka().obsahujePredmet("penezenka")) {
            penezenka.setVisible(true);
        } else {
            penezenka.setVisible(false);
        }

        if (hra.getHerniPlan().getLedvinka().obsahujePredmet("desetikoruna")) {
            desetikoruna.setVisible(true);
        } else {
            desetikoruna.setVisible(false);
        }

        if (hra.getHerniPlan().getRuka().obsahujePredmet("kyblik")) {
            kyblik.setVisible(true);
        } else {
            kyblik.setVisible(false);
        }
    }

    /**
     * Metoda slouží k zobrazení obrázku postavy
     * v případě její přítomnosti v lokaci
     */
    public void updatePostavy() {

        if (hra.getHerniPlan().getAktualniLokace().obsahujePostavu("bufetacka")) {
            bufetackaIm.setVisible(true);
        } else {
            bufetackaIm.setVisible(false);
        }

        if (hra.getHerniPlan().getAktualniLokace().obsahujePostavu("ucitel")) {
            ucitelIm.setVisible(true);
        } else {
            ucitelIm.setVisible(false);
        }
    }
}