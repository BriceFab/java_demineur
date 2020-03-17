package app.commun;

import app.main.menu.Game;

public enum Difficulty {
	 EASY,
	 NORMAL,
	 HARD;

	public String getName(Translate translate) {
		String s = super.toString();
		if (s.equals(Difficulty.EASY.name())) {
			return translate.getMessage(Code.rbEasy);
		} else if (s.equals(Difficulty.NORMAL.name())) {
			return translate.getMessage(Code.rbNormal);
		} else if (s.equals(Difficulty.HARD.name())) {
			return translate.getMessage(Code.rbHard);
		}
		return s;
	}
	
	public static Difficulty getDifficulty(String val, Translate translate) {
		for (Difficulty g : values()) {
			if (val.equals(g.getName(translate))) {
				return g;
			}
		}
		return null;
	}
	
	public static int getRatio(Game g, Difficulty diff) {
		switch (g) {
		case DEMINER:
				switch (diff) {
				case EASY: return 4;
				case NORMAL: return 3;
				case HARD: return 2;
				}
			break;
		case BBTAN:
			switch (diff) { //TODO
			case EASY: return 0;
			case NORMAL: return 0;
			case HARD: return 0;
			}
		}
		return 0;
	}
}
