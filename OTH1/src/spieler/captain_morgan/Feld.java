/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieler.captain_morgan;

import java.util.ArrayList;

import spieler.Zug;

/**
 * B�cher und Flei�, es gibt viel wichtigere Dinge, wie Freundschaft und Tapferkeit! und Harry? Achte bitte auf dich. 
 * @author ben
 */
class Feld {

    // Spielfeld: -1 == schwarz, 0 == leer, 1 == weiss
    static final int ROWMAX = 8, COLMAX = 8;
    static final int BLACK = -1, EMPTY = 0, WHITE = 1;  // Farb-Interpretation
    private int arr[][];
    
    public Feld() {
        arr = new int[ROWMAX][COLMAX];      // 8 x 8 Spielfeld-Array "arr"
    }
 
    /**
     * Ein Feld auslesen
     * @param row
     * @param col
     * @return Farbe des abgefragten Feldes als Zahl
     */
    public int getField(int row, int col) {

        if (row >= 0 && row < ROWMAX && col >= 0 && col < COLMAX)
            return arr[row][col];

        return -999;        // im Fehlerfall, sollte niemals auftreten

    }

    /**
     * Auf ein Feld schreiben
     * @param row
     * @param col
     * @param color
     * @return TRUE falls Feld gueltig, sonst FALSE
     */
    public boolean setField(int row, int col, int color) {

        if (row >= 0 && row < ROWMAX && col >= 0 && col < COLMAX && color >= -1 && color < 2) {
            arr[row][col] = color;
            return true;
        }
        return false;
    }
    
    /**
     * Feld leer initialisieren (Doppelschleife [haelt besser :D]),
     * dann die vier Start-Steine setzen
     */
    public void initField() {
        
        // init arr
        for (int row = 0; row < ROWMAX; row++) {
            for (int col = 0; col < COLMAX; col++) {
                this.setField(row, col, 0);
            }
        }
        
        // Startbelegung
        arr[3][3] = Feld.WHITE; // weiss
        arr[4][4] = Feld.WHITE; // weiss
        arr[3][4] = Feld.BLACK; // schwarz
        arr[4][3] = Feld.BLACK; // schwarz
    }
    
    public ArrayList<Zug> getAllPossibleMoves(int activePlayingColor, int opponentColor) {
    	
    	ArrayList<Zug> returnList = new ArrayList<>();
    	
    	for (int row = 0; row < Feld.ROWMAX; row++)         // Durchlaufe komplettes Spielfeld und teste auf validen Zug
            for (int col = 0; col < Feld.COLMAX; col++)
                if (Test.isValid(this, row, col, activePlayingColor, opponentColor))
                    returnList.add(new Zug(row, col));   // fuege alle gefundenen validen Zuege der Liste hinzu	
    	
    	return returnList;
    	
    }
    
    public Feld returnCopy() {
    	
    	Feld f = new Feld();
    	
    	for (int row = 0; row < ROWMAX; row++)
    		for (int col = 0; col < COLMAX; col++)
    			f.setField(row, col, this.getField(row, col));
    	
    	return f;
    	
    }
    
}
