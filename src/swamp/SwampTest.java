package swamp;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.TestCase;

public class SwampTest extends TestCase {
	
	Swamp testSwamp = Swamp.getSwamp();

	
	public void testGetSwampInt() {
		
		testSwamp.resetSwamp();
		
		int SIZE = 10;
		
		testSwamp = Swamp.getSwamp(SIZE);
		
		int expectedSize = SIZE;
		int actualSize = testSwamp.Grid.size();
		
		assertTrue(actualSize==expectedSize);
	}
	
	public void testGetSwampIntBig() {
		
		testSwamp.resetSwamp();
		
		int SIZE = 20;
		
		testSwamp = Swamp.getSwamp(SIZE);
		
		int expectedSize = SIZE;
		int actualSize = testSwamp.Grid.size();
		
		assertTrue(actualSize==expectedSize);
	}

	public void testGetAllowedMovements() {
		
		testSwamp.resetSwamp();
		testSwamp = Swamp.getSwamp(8);
		
		int rowNum = 3;
		int columnNum = 4;
		
		ArrayList<int[]> expectedMovements = new ArrayList<int[]>();
		ArrayList<int[]> actualMovements = new ArrayList<int[]>();
		
		expectedMovements.add(new int[] {2,3});
		expectedMovements.add(new int[] {2,4});
		expectedMovements.add(new int[] {2,5});
		expectedMovements.add(new int[] {3,3});
		expectedMovements.add(new int[] {3,5});
		expectedMovements.add(new int[] {4,3});
		expectedMovements.add(new int[] {4,4});
		expectedMovements.add(new int[] {4,5});
		
		actualMovements = Swamp.builder.getAllowedMovements(rowNum, columnNum, testSwamp);
		
		/*
		System.out.println("Expected movements");
		for (int[] coordinates : expectedMovements) {
			
		String out = Arrays.toString(coordinates);
		System.out.println(out);
		}		
		
		System.out.println("Actual movements");
		for (int[] coordinates : actualMovements) {
			
			String out = Arrays.toString(coordinates);
			System.out.println(out);
			}
		*/		
		if (expectedMovements.size()==actualMovements.size()){
			for (int i=0; i<actualMovements.size(); i++){
				int[] tempActual = actualMovements.get(i);
				int[] tempExpected = expectedMovements.get(i);
		
				assertTrue(Arrays.equals(tempActual, tempExpected));}}else{
			fail("Size of expected and actual allowed movements is different");}
		
	}
	
public void testGetAllowedMovementsBottomRight() {
		
		testSwamp.resetSwamp();
		testSwamp = Swamp.getSwamp(8);
		
		int rowNum = 8;
		int columnNum = 8;
		
		ArrayList<int[]> expectedMovements = new ArrayList<int[]>();
		ArrayList<int[]> actualMovements = new ArrayList<int[]>();
		
		expectedMovements.add(new int[] {7,7});
		expectedMovements.add(new int[] {7,8});
		expectedMovements.add(new int[] {8,7});
		
		actualMovements = Swamp.builder.getAllowedMovements(rowNum, columnNum, testSwamp);
		
	
		if (expectedMovements.size()==actualMovements.size()){
			for (int i=0; i<actualMovements.size(); i++){
				int[] tempActual = actualMovements.get(i);
				int[] tempExpected = expectedMovements.get(i);
		
				assertTrue(Arrays.equals(tempActual, tempExpected));}}else{
			fail("Size of expected and actual allowed movements is different");}
		
	}

public void testGetAllowedMovementsTopLeft() {
	
	testSwamp.resetSwamp();
	testSwamp = Swamp.getSwamp(8);
	
	int rowNum = 1;
	int columnNum = 1;
	
	ArrayList<int[]> expectedMovements = new ArrayList<int[]>();
	ArrayList<int[]> actualMovements = new ArrayList<int[]>();
	
	expectedMovements.add(new int[] {1,2});
	expectedMovements.add(new int[] {2,1});
	expectedMovements.add(new int[] {2,2});
	
	actualMovements = Swamp.builder.getAllowedMovements(rowNum, columnNum, testSwamp);
		
	if (expectedMovements.size()==actualMovements.size()){
		for (int i=0; i<actualMovements.size(); i++){
			int[] tempActual = actualMovements.get(i);
			int[] tempExpected = expectedMovements.get(i);
	
			assertTrue(Arrays.equals(tempActual, tempExpected));}}else{
		fail("Size of expected and actual allowed movements is different");}
	
}
	

//	public void testClearSwamp() {
//		int countBefore = 0;
//		int countAfter = 0;
//		testSwamp.resetSwamp();
//		testSwamp.getSwamp();
//		testSwamp.startGame();
//		for (int i=0; i<10; i++){
//			testSwamp.newRound();}
//		for (int row =1; row<=testSwamp.getSize(); row++) {
//			for (int column =1; column<=testSwamp.getSize(); column++) {
//				Square tempSquare = testSwamp.getThisSquare(row, column);
//				countBefore = countBefore + tempSquare.whoIsHere.size();}}
//		assertTrue(countBefore>0);
//		testSwamp.clearSwamp();
//		
//		
//		fail("Not yet implemented");
//	}

	public void testNewEnemy() {
		testSwamp.resetSwamp();
		testSwamp = Swamp.getSwamp();
		Square spawnSquare = testSwamp.getThisSquare(1, 1);
		assertTrue(spawnSquare.whoIsHere.size()==0);
		testSwamp.newEnemy();
		assertTrue(spawnSquare.whoIsHere.size()==1);
		testSwamp.newEnemy();
		assertTrue(spawnSquare.whoIsHere.size()==2);
	}

	public void testStartGame() {
		testSwamp.resetSwamp();
		testSwamp = Swamp.getSwamp();
		
		int countAll = 0;
		int ogreCounter = 0;
		
		for (int row =1; row<=testSwamp.getSize(); row++) {
			for (int column =1; column<=testSwamp.getSize(); column++) {
				Square tempSquare = testSwamp.getThisSquare(row, column);
				countAll = countAll + tempSquare.whoIsHere.size();}}
		assertTrue(countAll==0);
		
		testSwamp.startGame();
		
		for (int row =1; row<=testSwamp.getSize(); row++) {
			for (int column =1; column<=testSwamp.getSize(); column++) {
				Square tempSquare = testSwamp.getThisSquare(row, column);
				for (players.GameCharacter c : tempSquare.whoIsHere){
					if (c.getName().equals("Hek")){
						ogreCounter ++;} else {countAll++;}
					} 
				}}
		assertTrue(ogreCounter == 1);
		assertTrue(countAll==0);
	}

//	public void testNewRound() {
//		//TODO To implement
//		fail("Not yet implemented");
//	}

}
