package players;


import java.util.ArrayList;

import junit.framework.TestCase;

public class OgreTest extends TestCase {
	
	Ogre ogreTested = new Ogre("TestOgre");

	public void testIsEnemy() {
		assertTrue(!ogreTested.isEnemy());
	}
	
	public void testFightWon() {
		
		ArrayList<OgreEnemy> listEnemies = new ArrayList<OgreEnemy>();
		
		listEnemies.clear();
		
		OgreEnemy e = new Snake();
		
		listEnemies.add(e);
		
		assertTrue(ogreTested.performFight(listEnemies));
		
		
	}
	
	public void testFightLost(){
		
		ArrayList<OgreEnemy> listEnemies = new ArrayList<OgreEnemy>();
		
		OgreEnemy e = new Snake();
		OgreEnemy e2 = new Snake();
		OgreEnemy e3 = new Snake();
		OgreEnemy e4 = new Snake();
		
		listEnemies.add(e);
		listEnemies.add(e2);
		listEnemies.add(e3);
		listEnemies.add(e4);
		
		assertFalse(ogreTested.performFight(listEnemies));
		
	}

}
