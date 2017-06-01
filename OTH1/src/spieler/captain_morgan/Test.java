/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spieler.captain_morgan;

/**
 *
 * @author ben
 */
public class Test {

    /**
     * Testet, ob aktuell betrachtetes Feld LEER ist und ob irgendein Nachbarfeld GEGNERFARBE besitzt.
     * Falls ja, wird diese Richtung verfolgt, bis entweder ein LEERes Feld gefunden wird (Abbruch)
     * oder der Rand erreicht wird -> nichts passiert, nächster Schleifendurchlauf -> andere Richtung
     * oder man die EIGENE FARBE findet und somit ein valides Feld (aktuell betrachtetes) gefunden hat.
     * 
     * isValid wird fuer jede neue Richtung auf false gesetzt. Bei einem gueltigen Feld wird isValid = TRUE
     * und die Schleifen werden verlassen. -> isValid wird returned.
     * 
     * @param realRow
     * @param realCol
     * @param ownColor
     * @param opponentColor
     * @return TRUE falls valides Feld gefunden, sonst false
     */
    public static boolean isValid(Feld feld, int realRow, int realCol, int ownColor, int opponentColor) {

        boolean isValid = false;
        int tmpRow, tmpCol;
        int rowDif, colDif;

        // Durch diese doppelte Schleife wird die "Blickrichtung" bestimmt -> einmal im Kreis
        // rowDif = -1 ; colDif = 1 bedeutet z.B. + ein Feld nach oben-rechts
        for (rowDif = -1; rowDif < 2; rowDif++) {       // auessere Schleife (Zeilen-RICHTUNG(!))
            if (isValid) {
                break;
            }
            
            for (colDif = -1; colDif < 2; colDif++) {   // inmnere Schleife (Spalten-RICHTUNG(!))
                if (isValid) {
                    break;
                }
                
                if (rowDif == 0 && colDif == 0) {   // bei (0, 0) braucht nicht getestet werden -> keine Richtung
                    continue;
                }
                
                // Array-Grenzen prüfen
                if (realRow + rowDif >= 0 && realRow + rowDif < Feld.ROWMAX
                        && realCol + colDif >= 0 && realCol + colDif < Feld.COLMAX) {

                    // setze tmp Variablen
                    tmpRow = realRow;
                    tmpCol = realCol;

                    // aktuelles Feld leer && nächstes Feld in Richtung X == Gegner-Farbe?
                    if (feld.getField(tmpRow, tmpCol) == Feld.EMPTY
                            && feld.getField(tmpRow + rowDif, tmpCol + colDif) == opponentColor) {
                        
                        while (tmpRow + rowDif >= 0 && tmpRow + rowDif < Feld.ROWMAX
                                && tmpCol + colDif >= 0 && tmpCol + colDif < Feld.COLMAX) {
                            
                            // addiere + ein mal die aktuelle Richtung (ein Feld weit) zur aktuellen Position
                            // (Weg ablaufen und folgende Bedingungen pruefen)
                            tmpRow += rowDif;
                            tmpCol += colDif;

                            if (feld.getField(tmpRow, tmpCol) == opponentColor) {   // Gegnerfarbe? --> alles ok, continue;
                                continue;
                            }

                            if (feld.getField(tmpRow, tmpCol) == Feld.EMPTY) {      // LEER? --> Abbruch fuer DIESE RICHTUNG
                                break;
                            }

                            // setze isValid true bei gültigem Feld
                            if (feld.getField(tmpRow, tmpCol) == ownColor) {        // EIGENE Farbe? --> setze isValid = true;
                                isValid = true;
                            }
                        }
                    }
                }
            }
        }
        return isValid;     // return isValid (TRUE, falls valides Feld gefunden, sonst FALSE)
    }

    /**
     * Arbeitet aehnlich wie die isValid-Funktion, hat aber keinen return-Wert, sondern dreht in einer
     * weiteren WHILE-SChleife, welche den selben Weg zurueck laeuft, Steine um.
     * 
     * Farb-Reihenfolge in der Schnittstelle bestimmt, fuer wen umgedreht wird. (Fuer den ersten Farbwert)
     * 
     * @param realRow
     * @param realCol
     * @param ownColor
     * @param opponentColor 
     */
    public static void turnAround(Feld feld, int realRow, int realCol, int ownColor, int opponentColor) {

        int tmpRow, tmpCol;
        int rowDif, colDif;
        boolean pathCompleted = false;

        for (rowDif = -1; rowDif < 2; rowDif++)             // Kommentierung hier spaerlicher, da sehr aehnlich zu isValid -Fu.
            for (colDif = -1; colDif < 2; colDif++) {

                pathCompleted = false;

                // bei (0, 0) braucht nicht getestet werden -> keine Richtung
                if (rowDif == 0 && colDif == 0) {
                    continue;
                }

                // Array-Grenzen prüfen
                if (realRow + rowDif >= 0 && realRow + rowDif < 8
                        && realCol + colDif >= 0 && realCol + colDif < 8) {

                    // setze tmp Variablen
                    tmpRow = realRow;
                    tmpCol = realCol;

                    // nächstes Feld in Richtung X == Gegner-Farbe?
                    if (feld.getField(realRow + rowDif, realCol + colDif) == opponentColor) {

                        while (!pathCompleted
                                && tmpRow + rowDif >= 0 && tmpRow + rowDif < Feld.ROWMAX
                                && tmpCol + colDif >= 0 && tmpCol + colDif < Feld.COLMAX) {

                            tmpRow += rowDif;
                            tmpCol += colDif;

                            if (feld.getField(tmpRow, tmpCol) == opponentColor) {
                                continue;
                            }

                            if (feld.getField(tmpRow, tmpCol) == Feld.EMPTY) {
                                break;
                            }

                            if (feld.getField(tmpRow, tmpCol) == ownColor) {

                                while (tmpRow - rowDif >= 0 && tmpRow - rowDif < Feld.ROWMAX
                                        && tmpCol - colDif >= 0 && tmpCol - colDif < Feld.COLMAX) {
                                    
                                    // HIER ist der Unterschied zur isValid: man gehe rueckwarts
                                    tmpRow -= rowDif;
                                    tmpCol -= colDif;
                                    
                                    // man verlasse die Schleife, wenn man wieder am Anfang (eigene Farbe) steht
                                    if (feld.getField(tmpRow, tmpCol) == ownColor) {
                                        pathCompleted = true;   // Ein Mann setzt Abbruchbedingung :D
                                        break;
                                    }

                                    // Eine Funktion dreht Steine um (set own color)
                                    feld.setField(tmpRow, tmpCol, ownColor);
                                }
                            }
                        }
                    }
                }
            }
    }
}
