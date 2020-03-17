package app.game.deminer;

import app.commun.Code;
import app.commun.Translate;

public enum Size {
	Random,
	Cinq,
	Six,
	Sept,
	Huit,
	Neuf;
	
	public static int getNumber(Size s) {
		switch (s) {
		case Cinq: return 5;
		case Six:  return 6;
		case Sept: return 7;
		case Huit: return 8;
		case Neuf: return 9;
		default:   return 0;
		}
	}
	
	public String getName(Translate translate) {
		String s = super.toString();
		if (s.equals(Size.Random.name())) {
			return translate.getMessage(Code.rbRandom);
		} else if (s.equals(Size.Cinq.name())) {
			return translate.getMessage(Code.cbSizeCinq);
		} else if (s.equals(Size.Six.name())) {
			return translate.getMessage(Code.cbSizeSix);
		} else if (s.equals(Size.Sept.name())) {
			return translate.getMessage(Code.cbSizeSept);
		} else if (s.equals(Size.Huit.name())) {
			return translate.getMessage(Code.cbSizeHuit);
		} else if (s.equals(Size.Neuf.name())) {
			return translate.getMessage(Code.cbSizeNeuf);
		}
		return s;
	}
	
	public static Size getSize(String val, Translate translate) {
		for (Size s : values()) {
			if (val.equals(s.getName(translate))) {
				return s;
			}
		}
		return null;
	}
}
