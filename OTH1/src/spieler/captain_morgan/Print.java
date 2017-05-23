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
public class Print {

    static void out() {

        System.out.println();
        System.out.println("Aktuelle Belegung:\n");

        // column names
        System.out.print("  ");
        for (char c = 65; c < 65 + 8; c++)                                  // ASCII 65 == 'A'
            System.out.printf(" %c ", c);                                   // Buchstaben der Spalten (char++)
        
        // Spielfeld drucken
        System.out.println();
        for (int row = 0; row < Feld.ROWMAX; row++) {
            System.out.print(row + 1 + "|");                                // Zahlen links und linken Rand "zeichnen"
            for (int col = 0; col < Feld.COLMAX; col++) {
                if (Spieler.feld.getField(row, col) == Feld.BLACK) {
                    System.out.print(" X ");
                }
                else if (Spieler.feld.getField(row, col) == Feld.WHITE) {
                    System.out.print(" O ");
                }
                else {
                    System.out.print(" _ ");
                }
            }
            System.out.println("|");                                        // rechten Rand "zeichnen"
        }
        System.out.println("\n");
    }
}
