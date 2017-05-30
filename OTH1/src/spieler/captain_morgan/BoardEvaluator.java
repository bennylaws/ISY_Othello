package spieler.captain_morgan;

/**
 * 
 * @author Thomas
 * 
 * Zuständig für die Rückgabe der Einschätzung des Spielfelds.
 * Kennt die Grundmatrix des Spielbretts und wendet sie zunächst stumpf auf das Brett an.
 * Später: Maßzahl der Flexibilität: Zahl der möglichen eigenen Züge und Zahl der möglichen Züge des Gegners verrechnen.
 * Eventuell mit vermittelndem Faktor.
 * Sollte die Zahl der erfolgten Züge (also ohne Passen) zählen, um zu wissen, wie viele Felder noch frei sind.
 * Gegen Ende kann so vielleicht die Suchtiefe angepasst werden, um das tatsächliche Endszenario zu ermitteln.
 * (Ist die Suchtiefe am Anfang und am Ende gleich wichtig? --> variabel gestalten?)
 * 
 * Rückgabewert sollte positiv sein für uns und negativ für den gegnerischen Zug.
 * Evtl.: -maxInteger == Gegner gewinnt; +maxInteger == wir obsiegen
 * 
 * Existenz als alleinige Klasse ist fragwürdig.
 * 
 */
public class BoardEvaluator {

}
