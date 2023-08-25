package swamp;

import players.*;
import game.MessageHandler;

import java.util.Random;
import java.util.TreeMap;

public class Swamp {
	// Singleton Swamp class
	private static Swamp theSwamp;
	
	// Singleton Movements Handler
	private MovementsHandler moveHandler = MovementsHandler.getHandler();
	
	// Singleton Message Handler
	private MessageHandler messageHandler = MessageHandler.getMessageHandler();
	
	// Grid data structure
	public TreeMap<Integer, TreeMap<Integer, Square>> Grid;
	
	// Grid builder Class instance
	public static gridBuilder builder = new gridBuilder();

	private int size;
	private int roundCounter;

	public Ogre Hek;

	Random rand = new Random();

	private Swamp(int SIZE) {
		this.setSize(SIZE);
		this.Grid = builder.buildGrid(SIZE);
	};

	// When empty the constructor will use a default grid size of 4
	private Swamp() {
		this.setSize(4);
		this.Grid = builder.buildGrid(4);
	};

	public static Swamp getSwamp() {
		if (theSwamp == null) {
			theSwamp = new Swamp();
			builder.populateSquaresMoves(theSwamp);
		}
		return theSwamp;
	}

	public static Swamp getSwamp(int size) {
		if (theSwamp == null) {
			theSwamp = new Swamp(size);
			builder.populateSquaresMoves(theSwamp);
		}
		return theSwamp;
	}

	public Square getThisSquare(int row, int column) {
		return theSwamp.Grid.get(row).get(column);
	}

	// One in Three chance for an enemy to spawn on the Square in row 1 and
	// column 1
	public void newEnemy() {
		OgreEnemy tempEnemy = null;
		switch (rand.nextInt(3)) {
		case 0:
			tempEnemy = new Snake();
			break;
		case 1:
			tempEnemy = new Donkey();
			break;
		case 2:
			tempEnemy = new Parrot();
			break;
		default:

		}

		Square spawnSquare = theSwamp.getThisSquare(1, 1);
		spawnSquare.whoIsHere.add(tempEnemy);

	}

	// Clear the Swamp if populated, reset the round counter
	// and spawn a new Ogre
	public void startGame() {

		theSwamp.clearSwamp();

		theSwamp.roundCounter = 0;

		theSwamp = Swamp.getSwamp();
		if (Hek == null) {
			Hek = new Ogre("Hek");
		} else {
			Hek.setAlive(true);
		}

		Random rand = new Random();

		int low = 2;
		int high = theSwamp.getSize() + 1;

		int spawnHekRow = rand.nextInt(high - low) + low;
		int spawnHekColumn = rand.nextInt(high - low) + low;

		Square spawnHek = theSwamp.getThisSquare(spawnHekRow, spawnHekColumn);

		spawnHek.whoIsHere.add(Hek);
	};

	// If Hek is alive, check all squares and execute fights, stac movements and
	// execute them, spawn a new enemy at the end
	public void newRound() {
		if (!Hek.isAlive()) {

			messageHandler.displayMessage(
					"Hek died, to keep playing, please restart the game",
					"Game Over", 2);
			return;
		}

		roundCounter++;

		outerloop: for (int row = 1; row <= theSwamp.getSize(); row++) {
			for (int column = 1; column <= theSwamp.getSize(); column++) {
				Square tempSquare = theSwamp.getThisSquare(row, column);
				tempSquare.liveOrDie();
				if (!Hek.isAlive()) {

					theSwamp.gameOver();
					break outerloop;
				}
				tempSquare.stackMove();
			}

		}
		moveHandler.executeMovements();
		if (rand.nextInt(3) == 0) {
			theSwamp.newEnemy();
		}
	};

	// Clear all characters from the swamp
	private void clearSwamp() {
		for (int row = 1; row <= theSwamp.getSize(); row++) {
			for (int column = 1; column <= theSwamp.getSize(); column++) {
				Square tempSquare = theSwamp.getThisSquare(row, column);
				tempSquare.whoIsHere.clear();
			}
		}
	}

	// Creates the game over message to display
	public void gameOver() {
		String name = this.Hek.getName();
		String roundNum = Integer.toString(this.roundCounter);
		String gameOverMessage = name + " died after " + roundNum + " rounds.";

		messageHandler.displayMessage(gameOverMessage, "Game Over", 2);

		theSwamp.clearSwamp();

	}

	// Interact with the message handler to display messages in the gui
	public void sendMessage(String message, String title, int typeMessage) {
		messageHandler.displayMessage(message, title, typeMessage);
	}

	// Reset the singleton, used for testing
	protected void resetSwamp() {

		theSwamp = null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
