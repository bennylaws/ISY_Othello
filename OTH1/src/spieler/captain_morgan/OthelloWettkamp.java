/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieler.captain_morgan;

import java.util.ArrayList;
import java.util.List;
import spieler.OthelloSpieler;

/**
 *
 * @author ben niiiicht --> Priemer
 */
public class OthelloWettkamp {
	
	/// HATE GIT !!! ///
	/// I REALLY DO NOW ///
	///It will get better.///

    public static void main(String[] args) {
        /* Es muss ein OthelloArena-Objekt erzeugt
     * werden. Bei der Erzeugung werden die am Wettkampf
     * teilnehmenden Spieler in Form einer Liste von
     * Othello-Spielern uebergeben. Durch die Erzeugung des
     * OthelloArena-Objekts wird der Wettkampf gestartet.
         */

        //Spielerliste aufbauen
        List<OthelloSpieler> spieler = new ArrayList<> ();

        // Spielfeld
        Feld feld = new Feld();
    
        //Die Spieler
//    spieler.add(new spieler.Referenzspieler( )); //Suchtiefe Default
        spieler.add (new spieler.captain_morgan.Spieler ());
        spieler.add (new spieler.Referenzspieler (1)); //Suchtiefe 9

        new rahmen.OthelloArena (150,   //Gesamtbedenkzeit in Sekunden 
                spieler,                //Spielerliste 
                false);                 //debug aus
    }
}
