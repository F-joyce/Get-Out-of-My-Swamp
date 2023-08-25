package players;

public abstract class GameCharacter {

	protected boolean enemy = false;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnemy() {
		return enemy;
	}

	protected void setEnemy(boolean enemy) {
		this.enemy = enemy;
	}
}
