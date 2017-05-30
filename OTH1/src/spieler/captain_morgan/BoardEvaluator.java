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
	
	static final int [][] evaluationMatrix = new int[][]{
		{50, -1, 3, 3, 3, 3, -1, 50},
		{-1, -5, 2, 2, 2, 2, -5, -1},
		{3, 2, 2, 2, 2, 2, 2, 3},
		{3, 2, 2, 0, 0, 2, 2, 3},
		{3, 2, 2, 0, 0, 2, 2, 3},
		{3, 2, 2, 2, 2, 2, 2, 3},
		{-1, -5, 2, 2, 2, 2, -5, -1},
		{50, -1, 3, 3, 3, 3, -1, 50}
	};
	
	static public int getBoardValue(Feld boardToEval,int testColor, int oppColor){
		
		int ret = 0;
		for(int i = 0; i < 8 ; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(boardToEval.getField(i, j) == testColor)
				{
					ret += evaluationMatrix[i][j];
				}
				else if(boardToEval.getField(i, j) == oppColor)
					ret -= evaluationMatrix[i][j];
			}
		}
		
		return ret;
	};

	
	
}
