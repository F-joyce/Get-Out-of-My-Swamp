package swamp;

import players.*;
import junit.framework.TestCase;

public class MovementTest extends TestCase {

	public void testGo() {
		
		Square s = new Square();
		Donkey d = new Donkey();
		
		Movement m = new Movement(d, s);
		
		assertTrue(s.whoIsHere.size()==0);
		
		m.go();
		
		assertTrue(s.whoIsHere.get(0)==d);
		
	}

}
