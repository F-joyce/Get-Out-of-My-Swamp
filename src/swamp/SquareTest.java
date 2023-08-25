package swamp;

import junit.framework.TestCase;
import players.*;

public class SquareTest extends TestCase {

	public void testLiveOrDie() {
		Square testSquare = new Square();

		Ogre o = new Ogre();

		Snake s = new Snake();

		testSquare.whoIsHere.add(o);
		testSquare.whoIsHere.add(s);

		assertTrue(testSquare.whoIsHere.size() == 2);

		testSquare.liveOrDie();

		assertTrue(testSquare.whoIsHere.size() == 1);
		assertTrue(testSquare.whoIsHere.get(0) == o);

	}

	public void testLiveOrDieLost() {
		Square testSquare = new Square();

		Ogre o = new Ogre();

		Snake s = new Snake();
		Snake s1 = new Snake();
		Snake s2 = new Snake();

		testSquare.whoIsHere.add(o);
		testSquare.whoIsHere.add(s);
		testSquare.whoIsHere.add(s1);
		testSquare.whoIsHere.add(s2);

		assertTrue(testSquare.whoIsHere.size() == 4);

		testSquare.liveOrDie();

		assertTrue(testSquare.whoIsHere.size() == 4);
		assertTrue(!o.isAlive());

	}

	public void testStackMove() {
		MovementsHandler mHandler = MovementsHandler.getHandler();
		Swamp sw = Swamp.getSwamp();

		Snake s = new Snake();
		Square sq = sw.getThisSquare(2, 2);

		sq.whoIsHere.add(s);

		assertTrue(sq.whoIsHere.size() == 1);
		assertTrue(mHandler.theMovements.size() == 0);

		sq.stackMove();

		assertTrue(mHandler.theMovements.size() == 1);
		assertTrue(sq.whoIsHere.size() == 0);

	}

}
