package players;

import java.util.ArrayList;

public class Ogre extends GameCharacter {

	protected FightBehaviour mood;

	public boolean alive = true;

	// Constructor
	public Ogre() {
		this.setMood(new Passive());
	}

	// Constructor overload with name
	public Ogre(String name) {

		this.setName(name);
		this.setMood(new Passive());
	}

	// Program to the interface, call the fight method of FightBehaviour
	// interface
	public boolean performFight(ArrayList<OgreEnemy> tempEnemies) {
		boolean outcome = this.mood.fight(tempEnemies);
		this.setAlive(outcome);
		return outcome;
	}

	// Instantiate the fight behaviour mood parameter with instances of
	// different classes implementing the interface, Startegy pattern
	public void Passive() {
		this.setMood(new Passive());
	}

	public void Grumpy() {
		this.setMood(new Grumpy());
	};

	public FightBehaviour getMood() {
		return mood;
	}

	public void setMood(FightBehaviour mood) {
		this.mood = mood;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
