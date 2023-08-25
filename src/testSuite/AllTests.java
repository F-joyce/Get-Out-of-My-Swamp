package testSuite;

import players.OgreTest;
import swamp.MovementTest;
import swamp.SquareTest;
import swamp.SwampTest;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		suite.addTestSuite(OgreTest.class);
		suite.addTestSuite(SquareTest.class);
		suite.addTestSuite(SwampTest.class);
		suite.addTestSuite(MovementTest.class);
		// $JUnit-BEGIN$

		// $JUnit-END$
		return suite;
	}

}
