package swamp;

public class Movement {

	MovementsHandler moveHandler = MovementsHandler.getHandler();

	private players.GameCharacter c;

	private Square s;

	public Movement(players.GameCharacter c, Square s) {
		this.c = c;
		this.s = s;
	}

	public void stack() {
		moveHandler.stackMovement(this);
	}

	public void go() {
		s.whoIsHere.add(c);

	}

}
