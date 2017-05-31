/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Farbe;
import spieler.OthelloSpieler;
import spieler.Zug;
import spieler.ZugException;

/**
 *
 * @author ben
 */
public class Spieler implements OthelloSpieler {
    
    static int ownColor = -1, opponentColor = 1;    // -1 == black, 1 == white
    int suchTiefe;
    static Feld feld;
    
    // Liste für mögliche Zuege ( fuerrandom Auswahl)
    ArrayList <Zug> possibleMoves = new ArrayList<>();

    public Spieler () {
    	this.suchTiefe = 9;
    }
    
    public Spieler(int suchTiefe) {
    	this.suchTiefe = suchTiefe;
    }
    
    @Override
    public Zug berechneZug(Zug lastMove, long f, long l1) throws ZugException {

        if (lastMove != null && lastMove.getSpalte() != -1) {
            
            // Gegner-Zug setzen
            feld.setField(lastMove.getZeile(), lastMove.getSpalte(), opponentColor);
            
            // Gegner Chips umdrehen - Farb-Reihenfolge bestimmt in dieser Fu. fuer wen umgedreht wird
            Test.turnAround(feld, lastMove.getZeile(), lastMove.getSpalte(), opponentColor, ownColor);
        }
        
//        Print.out();        // Feld nach Gegner move ausdrucken
        
        // Zug fuer die Rueckgabe an Priemer erstellen
        // (-1, -1) bedeutet "Passen" --> wird returned, falls kein gültiges Feld gefunden wird
        Zug ownMove;
        
        /////////////////////////////////////////////////

        Feld feldCopy = feld.returnCopy();
        GameTree gt = new GameTree();
        gt.addRoot(feldCopy, suchTiefe);
        ownMove = gt.getBestMove();
        System.out.println("Zug: (" + ownMove.getZeile() + ", " + ownMove.getSpalte() + ")");
        /////////////////////////////////////////////////
        
        // hier eigene Chips umdrehen
        if (ownMove.getSpalte() != -1) {                                                        // wenn nicht -1 --> wenn nicht gepasst wird
            feld.setField(ownMove.getZeile(), ownMove.getSpalte(), ownColor);                   // dann wird tatsächlich unser move aufs Feld geschrieben
            Test.turnAround(feld, ownMove.getZeile(), ownMove.getSpalte(), ownColor, opponentColor);  // und dann werden Steine zu unseren Gunsten gedreht
            
        }
//        Print.out();        // Feld nach eigenem move ausdrucken
        return ownMove;     // Rückgabe unseres Zuges an Priemers Spielrahmen (an den prima Spielrahmen  =) )
    }

    @Override
    public void neuesSpiel(Farbe ownColor, int timeLeft) {  // Priemer teilt uns eine Farbe zu

        // neues Spielfeld und init
        feld = new Feld();    
        feld.initField();   // Feld Startbelegung vornehmen
    
        // setze Farben
        if (ownColor.equals(Farbe.SCHWARZ)) {
            Spieler.ownColor = Feld.BLACK;
            opponentColor = Feld.WHITE;
        }
        else {
            Spieler.ownColor = Feld.WHITE;
            opponentColor = Feld.BLACK;
        }
    }

    @Override
    public String meinName() {
        return "CaptainMorgan"; // return name
    }
}
