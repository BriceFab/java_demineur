package app.main.menu;

import app.commun.Code;
import app.commun.Environment;
import app.commun.Translate;
import app.game.bbtan.ctrlBbtan;
import app.game.deminer.Deminer;
import app.game.bbtan.Bbtan;
import app.game.deminer.ctrlDeminer;

public enum Game {
	DEMINER,
	BBTAN;
	
	public String getName(Translate translate) {
		String s = super.toString();
		if (s.equals(Game.DEMINER.name())) {
			return translate.getMessage(Code.gameDeminer);
		} else if (s.equals(Game.BBTAN.name())) {
			return translate.getMessage(Code.gameBBTAN);
		}
		return s;
	}
	
	public static Game getGame(String val, Translate translate) {
		for (Game g : values()) {
			if (val.equals(g.getName(translate))) {
				return g;
			}
		}
		return null;
	}
	
	public static Boolean isLoggedRequire(Game g) {
		switch (g) {
			case DEMINER:
				return ctrlDeminer.isLoggedRequire();
			case BBTAN:
				return ctrlBbtan.isLoggedRequire();
			default: return false;
		}
	}
	
	public static Boolean haveProgressBar(Game g) {
		switch (g) {
			case DEMINER:
				return haveProgessBar(Deminer.env);
			case BBTAN:
				return haveProgessBar(Bbtan.env);
			default: return false;
		}
	}
	
	private static Boolean haveProgessBar(Environment env) {
		if (env == Environment.DEV)
			return false;
		return true;
	}
}
