package com.github.martinweisser.Skola.ui;

import com.github.martinweisser.Skola.logika.IHra;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
//import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @authors Filip Vencovsky, Martin Weisser
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	//@FXML private MenuItem konec;
	
	private IHra hra;
	
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		
		/*if(hra.konecHry()) {
			vystup.appendText("\n\n Konec hry \n");
			vstupniText.setEditable(false);
		}*/

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
		hra.setKonecHry(true);
		//hra.Hra();
		vstupniText.setEditable(true);
		vystup.setText(hra.vratUvitani());
	}
	
	
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		//vystup.appendText(hra.vratUvod());
		vystup.setEditable(false);
		this.hra = hra;
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}