package swamp;

import java.util.ArrayList;

//Use the Command Pattern to stack movements in an arraylist and execute them all when appropriate
public class MovementsHandler {
	
	private static MovementsHandler theHandler;
	
	protected ArrayList<Movement> theMovements;
	
	private MovementsHandler(){
		theMovements = new ArrayList <Movement>();
	}
	
	public static MovementsHandler getHandler() {
		if (theHandler == null) {
			theHandler = new MovementsHandler();
		}
		return theHandler;
	}
	
	
	public void stackMovement(Movement theMovement) {
		theMovements.add(theMovement);
	}
	
	public void executeMovements(){
		for (Movement move : theMovements) {
			move.go();
		}
		theMovements.clear();
	}

}
