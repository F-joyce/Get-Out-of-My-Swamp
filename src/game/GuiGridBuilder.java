package game;

import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GuiGridBuilder {

	public GuiGridBuilder() {
	}

	protected TreeMap<Integer, JLabel> getRowGui(int SIZE, int rowNum) {

		TreeMap<Integer, JLabel> tempRow = new TreeMap<Integer, JLabel>();

		for (int columnNum = 1; columnNum <= SIZE; columnNum++) {

			JLabel tempLabel = new JLabel("" + rowNum + "-" + columnNum);

			tempRow.put(columnNum, tempLabel);
		}

		return tempRow;
	}

	protected TreeMap<Integer, TreeMap<Integer, JLabel>> buildGridGUI(int SIZE) {

		TreeMap<Integer, TreeMap<Integer, JLabel>> theGrid = new TreeMap<Integer, TreeMap<Integer, JLabel>>();

		for (int rowNum = 1; rowNum <= SIZE; rowNum++) {

			theGrid.put(rowNum, getRowGui(SIZE, rowNum));

		}

		return theGrid;
	}

	protected void populateGrid(int SIZE, JPanel swampPanel,
			TreeMap<Integer, TreeMap<Integer, JLabel>> theGrid) {
		for (int row = 1; row < SIZE + 1; row++) {
			TreeMap<Integer, JLabel> tempRow = theGrid.get(row);
			for (int column = 1; column < SIZE + 1; column++) {
				JLabel tempLabel = tempRow.get(column);
				tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
				swampPanel.add(tempLabel);
			}
		}
	}
}
