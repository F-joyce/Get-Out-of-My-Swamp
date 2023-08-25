package players;

import java.util.ArrayList;

import swamp.Swamp;

public class Grumpy implements FightBehaviour {

	public boolean fight(ArrayList<OgreEnemy> tempEnemies) {
		int numEnemies = tempEnemies.size();
		//If more enemies than Hek can handle, return false, Hek lost the fight
		if (numEnemies >= 3) {

			return false;
		} else {
			//Create the message to display and return true when Hek wins
			String out = "Hek killed ";
			if (numEnemies == 1) {
				for (OgreEnemy e : tempEnemies) {
					out = out + "a " + e.getName();
				}
			} else {
				out = out + tempEnemies.size() + " enemies.";
			}

			Swamp.getSwamp().sendMessage(out, "Nice!", 1);
			return true;
		}
	}
}
