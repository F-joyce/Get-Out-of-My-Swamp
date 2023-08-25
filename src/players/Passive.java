package players;

import java.util.ArrayList;

import swamp.Swamp;

public class Passive implements FightBehaviour {

	public boolean fight(ArrayList<OgreEnemy> tempEnemies) {
		int numEnemies = tempEnemies.size();
		if (numEnemies >= 2) {
			
			return false;
			} else {
				String out = "Hek killed ";
				if (numEnemies == 1){
				for (OgreEnemy e : tempEnemies){
					out = out + "a " + e.getName();}} else {
						out = out + tempEnemies.size() + " enemies.";
					}
				
				Swamp.getSwamp().sendMessage(out, "Nice!", 1);
				return true;
				}}}
