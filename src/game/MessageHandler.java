package game;

import java.util.ArrayList;

public class MessageHandler implements Observable {

	// Singleton pattern, the message handler is used in the game when is
	// necessary to display messages in the GUI, displaying messages as needed
	private static MessageHandler theHandler;

	private ArrayList<SwampGame> games;

	private MessageHandler() {
	}

	public static MessageHandler getMessageHandler() {
		if (theHandler == null) {
			theHandler = new MessageHandler();
			theHandler.games = new ArrayList<SwampGame>();
		}
		return theHandler;
	}

	public void addGame(SwampGame game) {
		theHandler.games.add(game);
	}

	public void displayMessage(String message, String title, int typeIcon) {
		for (SwampGame game : games) {
			// this if statement allows to avoid errors if no GUI is connected
			if (game != null) {
				game.display(message, title, typeIcon);
			}
		}
	}

	public void removeGame(SwampGame game) {
		theHandler.games.remove(game);

	}
}
