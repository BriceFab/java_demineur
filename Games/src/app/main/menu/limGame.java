package app.main.menu;

import javax.swing.AbstractListModel;

import app.commun.Translate;

@SuppressWarnings("serial")
public class limGame extends AbstractListModel<String> {
	
	private String[] games;
	
	public limGame() {
		this(Game.values().length);
	}
	
	public limGame(int size) {
		games = new String[size];
	}
	
	@Override
	public String getElementAt(int index) {
		return games[index];
	}

	@Override
	public int getSize() {
		return games.length;
	}
	
	public void changeLang(Translate translate) {
		for (int i = 0; i < Game.values().length; i++) {
			games[i] = Game.values()[i].getName(translate);
		}
	}

}
