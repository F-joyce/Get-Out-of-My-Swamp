package game;

public interface Observable {
	public void addGame(SwampGame game);

	public void removeGame(SwampGame game);

	public void displayMessage(String message, String title, int typeIcon);
}
