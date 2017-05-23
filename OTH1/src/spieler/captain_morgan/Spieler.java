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
    static Feld feld;
    
    // Liste für mögliche Zuege ( fuerrandom Auswahl)
    ArrayList <Zug> possibleMoves = new ArrayList();

    @Override
    public Zug berechneZug(Zug lastMove, long f, long l1) throws ZugException {

        if (lastMove != null && lastMove.getSpalte() != -1) {
            
            // Gegner-Zug setzen
            feld.setField(lastMove.getZeile(), lastMove.getSpalte(), opponentColor);
            
            // Gegner Chips umdrehen - Farb-Reihenfolge bestimmt in dieser Fu. fuer wen umgedreht wird
            Test.turnAround(lastMove.getZeile(), lastMove.getSpalte(), opponentColor, ownColor);
        }
        
        Print.out();        // Feld nach Gegner move ausdrucken
        
        // Zug fuer die Rueckgabe an Priemer erstellen
        // (-1, -1) bedeutet "Passen" --> wird returned, falls kein gültiges Feld gefunden wird
        Zug ownMove = new Zug(-1, -1);
        
        //possibleMoves auslagern, damit wir von anderswo geschmeidig Zugriff drauf haben.
        possibleMoves.clear();      // lösche Zuege der letzten Runde
        
        for (int row = 0; row < Feld.ROWMAX; row++)         // Durchlaufe komplettes Spielfeld und teste auf validen Zug
            for (int col = 0; col < Feld.COLMAX; col++)
                if (Test.isValid(row, col, ownColor, opponentColor))
                    possibleMoves.add(new Zug(row, col));   // fuege alle gefundenen validen Zuege der Liste hinzu
            
        int possMovCount = possibleMoves.size();            // Anzahl der gefundenen validen Zuege
        System.out.println("* Testausgabe Anzahl valide Zuege: " + possMovCount + " *");
        
        int pickMoveFromList = (int) (Math.random() * (possMovCount - 1));  // suche zufällig Zug aus --> 0 bis (Groesse der Liste - 1)
        System.out.println("* Testausgabe random number: " + pickMoveFromList + " *");
        
        if (possMovCount > 0) {                             // falls Liste valide Zuege enthaelt (mindestens 1)
//            if (possibleMoves.contains())                 // hier noch feldbewertung einfliessen lassen (Prioritaeten)
            ownMove = possibleMoves.get(pickMoveFromList);  // speichere zufaellig gewaehlten Zug in "Zug ownMove" (wird an Spielrahmen (Priemer) returned)
        }
        // hier eigene Chips umdrehen
        if (ownMove.getSpalte() != -1) {                                                        // wenn nicht -1 --> wenn nicht gepasst wird
            feld.setField(ownMove.getZeile(), ownMove.getSpalte(), ownColor);                   // dann wird tatsächlich unser move aufs Feld geschrieben
            Test.turnAround(ownMove.getZeile(), ownMove.getSpalte(), ownColor, opponentColor);  // und dann werden Steine zu unseren Gunsten gedreht
            
        }
        Print.out();        // Feld nach eigenem move ausdrucken
        return ownMove;     // Rückgabe unseres Zuges an Priemers Spielrahmen (an den prima Spielrahmen  =) )
    }

    @Override
    public void neuesSpiel(Farbe ownColor, int timeLeft) {  // Priemer teilt uns eine Farbe zu

        // neues Spielfeld und init
        feld = new Feld();    
        feld.initField();   // Feld Startbelegung vornehmen
    
        // setze Farben
        if (ownColor.equals(Farbe.SCHWARZ)) {
            this.ownColor = Feld.BLACK;
            opponentColor = Feld.WHITE;
        }
        else {
            this.ownColor = Feld.WHITE;
            opponentColor = Feld.BLACK;
        }
    }

    @Override
    public String meinName() {
        return "CaptainMorgan"; // return name
    }
}
