package swamp;

import players.*;
import players.GameCharacter;

import java.util.ArrayList;
import java.util.Random;

import players.Ogre;

public class Square {
	// Store all character in the square
	public ArrayList<GameCharacter> whoIsHere = new ArrayList<GameCharacter>();

	// Store possible moves from this square as coordinates
	ArrayList<int[]> allowedMovements;

	public Square() {
		this.allowedMovements = new ArrayList<int[]>();
	}

	// Check if there is an Ogre and enemies in the same square
	public void liveOrDie() {
		Ogre ogreContainer = null;

		ArrayList<OgreEnemy> tempEnemies = new ArrayList<OgreEnemy>();

		for (GameCharacter isIn : this.whoIsHere) {
			if (isIn.getClass() == Ogre.class) {
				ogreContainer = (Ogre) isIn;
			} else {

				tempEnemies.add((OgreEnemy) isIn);
			}
		}
		;
		// if there are both Ogre and enemies we let the Ogre fight
		if (ogreContainer != null && tempEnemies.size() > 0) {
			// True will be return if the Ogre wins, and we remove the enemies
			// from the game
			if (ogreContainer.performFight(tempEnemies)) {
				this.whoIsHere.removeAll(tempEnemies);
			}

		}

	}

	public void stackMove() {

		for (GameCharacter c : this.whoIsHere) {

			Swamp theSwamp = Swamp.getSwamp();

			Random rand = new Random();
			//Choosing the next movement at random from the list of possible movements
			int index = rand.nextInt(this.allowedMovements.size());
			//Get the coordinate of the movement
			int[] coordinates = this.allowedMovements.get(index);
			
			//Select the square instance using the coordinates chose
			Square moveHere = theSwamp.getThisSquare(coordinates[0],
					coordinates[1]);
			//Store the information for the movement in a Movement instance, stores it in the movement handler
			Movement tempMove = new Movement(c, moveHere);
			tempMove.stack();
		}
		//Clear the character list of the square after the movements of each have been stacked
		this.whoIsHere.clear();
	}

}
