package swamp;

import java.util.ArrayList;
import java.util.TreeMap;

public class gridBuilder {

	// The builder creates the game grid using a data structure with a TreeMap
	// nested in another Treemap
	public gridBuilder() {
	}

	private TreeMap<Integer, Square> getRow(int SIZE, int rowNum) {

		TreeMap<Integer, Square> tempRow = new TreeMap<Integer, Square>();

		for (int columnNum = 1; columnNum <= SIZE; columnNum++) {

			Square square = new Square();

			tempRow.put(columnNum, square);
		}

		return tempRow;
	}

	public TreeMap<Integer, TreeMap<Integer, Square>> buildGrid(int SIZE) {

		TreeMap<Integer, TreeMap<Integer, Square>> theGrid = new TreeMap<Integer, TreeMap<Integer, Square>>();

		for (int rowNum = 1; rowNum <= SIZE; rowNum++) {

			theGrid.put(rowNum, getRow(SIZE, rowNum));

		}

		return theGrid;
	}

	// This method calculates the allowed movement from each square and store
	// them as an arraylist of arrays of coordinates in each square when the
	// grid is created
	public ArrayList<int[]> getAllowedMovements(int rowNum, int columnNum,
			Swamp theSwamp) {

		ArrayList<int[]> allowedMovements = new ArrayList<int[]>();

		for (int xRow = rowNum - 1; xRow <= rowNum + 1; xRow++) {
			// Check the movement don't go out of the Swamp boundaries
			if (xRow != 0 && xRow <= theSwamp.getSize()) {
				for (int yColumn = columnNum - 1; yColumn <= columnNum + 1; yColumn++) {
					// Check the movement don't go out of the Swamp boundaries
					if (yColumn != 0 && yColumn <= theSwamp.getSize()) {
						// Avoid to move to the same square
						if (xRow != rowNum | yColumn != columnNum) {
							int[] allowed = { xRow, yColumn };
							//If all conditions are respected we store the allowed coordinates
							allowedMovements.add(allowed);
						}
					} // End insert coordinates
				} // End column loop
			}
		} // End row loop

		return allowedMovements;
	}

	
	public void populateSquaresMoves(Swamp theSwamp) {

		for (int rowNum = 1; rowNum <= theSwamp.getSize(); rowNum++) {
			for (int columnNum = 1; columnNum <= theSwamp.getSize(); columnNum++) {
				Square tempSquare = theSwamp.getThisSquare(rowNum, columnNum);
				ArrayList<int[]> tempMoves = getAllowedMovements(rowNum,
						columnNum, theSwamp);
				tempSquare.allowedMovements.addAll(tempMoves);
			}

		}

	}

}
