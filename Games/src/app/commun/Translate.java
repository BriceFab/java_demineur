package app.commun;

public class Translate {	
	private Lang actLang;
	
	public Translate(Lang lang) {
		this.actLang = lang;
	}
	
	public Translate() {
		this(Lang.EN);
	}
	
	public String getMessage(String code) {
		return getTraduction(code);
	}
	
	public String getMessage(String code, String[] params) {
		String sMessage = getMessage(code);
		if (sMessage != null) {
			for (String param : params) {
				sMessage = sMessage.replaceFirst("xxx", param);
			}
		}
		return sMessage;
	}
	
	public String getMessage(String code, String param) {
		String sMessage = getMessage(code);
		if (sMessage != null) {
			sMessage = sMessage.replaceFirst("xxx", param);
		}
		return sMessage;
	}
	
	public void changeLang(Lang lang) {
		this.actLang = lang;
	}
	
	public Lang getLang() {
		return this.actLang;
	}
	
	private String getTraduction(String code) {	
		switch (actLang) {
		case EN:
			switch (code) {
				case Code.btnStart: return "Start";
				case Code.btnRestart: return "Restart";
				case Code.btnStop: return "Stop";
				case Code.btnClose: return "Close";
				case Code.mnuDiff: return "Difficulty";
				case Code.rbEasy: return "Easy";
				case Code.rbNormal: return "Normal";
				case Code.rbHard: return "Hard";
				case Code.mnuSize: return "Size";
				case Code.rbRandom: return "Random";
				case Code.mnuColor: return "Color";
				case Code.rbDefault: return "Default";
				case Code.rbCustom: return "Custom";
				case Code.mnuLanguage: return "Language";
				case Code.mniImport: return "Import";
				case Code.mniExport: return "Export";
				case Code.mnuConfig: return "Config";
				case Code.cbRefreshNumber: return "Refresh Number";
				case Code.cbTimer: return "Timer visible";
				case Code.sAlreadyStart: return "The game is already start, click restart";
				case Code.sGameNotStart: return "The game is not start, click on start";
				case Code.sAlreadyStop: return "The game is already stop";
				case Code.sDiffChange: return "The difficulty has been changed to xxx. Click on start";
				case Code.sSizeChange: return "The size has been changed to xxx. Click on start";
				case Code.sGameOver: return "GameOver! You have touched xxx/xxx boxes";
				case Code.sGameWin: return "End, Won! You have hit all the boxes! (xxx)";
				case Code.sTimerVisible: return "The timer is visible";
				case Code.sTimePlayed: return "Time played: xxx seconds";
				case Code.sTitlePortal: return "Portal";
				case Code.btnPlay: return "Play";
				case Code.sLogin: return "Login";
				case Code.gameDeminer: return "Deminer";
				case Code.gameBBTAN: return "BBTAN";
				case Code.notLogin: return "You are not log in!";
				case Code.gameChosen: return "You chose xxx. Click on play";
				case Code.noGameChosen: return "Please, chose the game!";
				case Code.loginAs: return "Connected as xxx.";
				case Code.sAuth: return "Authentification";
				case Code.sId: return "Identifier";
				case Code.sPassword: return "Password";
				case Code.sCompleteAllField: return "Please complete all fields!";
				case Code.sLoginInvalide: return "Invalid username or password!";
				case Code.sMustLogged: return "You must be logged in to play xxx!";
				case Code.error: return "Error..";
				case Code.firstIsSafe: return "First case is safe..";
				case Code.cbProgressBar: return "Loading Bar";
				case Code.mnuOption: return "Options";
				case Code.cbSizeCinq: return "5x5";
				case Code.cbSizeSix: return "6x6";
				case Code.cbSizeSept: return "7x7";
				case Code.cbSizeHuit: return "8x8";
				case Code.cbSizeNeuf: return "9x9";
				case Code.mnuInfo: return "Information";
				case Code.btnPause: return "Pause";
				case Code.btnCancel: return "Cancel";

			}
		case FR:
			switch (code) {
				case Code.btnStart: return "Commencer";
				case Code.btnRestart: return "Recommencer";
				case Code.btnStop: return "Arr�ter";
				case Code.btnClose: return "Fermer";
				case Code.mnuDiff: return "Difficult�";
				case Code.rbEasy: return "Facile";
				case Code.rbNormal: return "Moyen";
				case Code.rbHard: return "Dur";
				case Code.mnuSize: return "Taille";
				case Code.rbRandom: return "Al�atoire";
				case Code.mnuColor: return "Couleur";
				case Code.rbDefault: return "D�faut";
				case Code.rbCustom: return "Personnalis�";
				case Code.mnuLanguage: return "Language";
				case Code.mniImport: return "Importer";
				case Code.mniExport: return "Exporter";
				case Code.mnuConfig: return "Config";
				case Code.cbRefreshNumber: return "Actualiser nombre";
				case Code.cbTimer: return "Timer visible";
				case Code.sAlreadyStart: return "Le jeu est d�j� start, cliquez sur recommencer";
				case Code.sGameNotStart: return "Le jeu n'est pas d�marrer, cliquez sur commencer";
				case Code.sAlreadyStop: return "Le jeu est d�j� stopp�";
				case Code.sDiffChange: return "La difficutl� a �t� chang�e en xxx. Cliquez sur commencer";
				case Code.sSizeChange: return "La taille a �t� chang�e en xxx. Cliquez sur commencer";
				case Code.sGameOver: return "Fin, Perdu ! Vous avez touch� xxx/xxx cases";
				case Code.sGameWin: return "Fin, Gagn� ! Vous avez touch� toutes les cases! (xxx)";
				case Code.sTimerVisible: return "Le timer est visible";
				case Code.sTimePlayed: return "Temps jou�: xxx secondes";
				case Code.sTitlePortal: return "Portail";
				case Code.btnPlay: return "Jouer";
				case Code.sLogin: return "Connexion";
				case Code.gameDeminer: return "D�mineur";
				case Code.gameBBTAN: return "BBTAN";
				case Code.notLogin: return "Vous n'�tes pas connect�!";
				case Code.gameChosen: return "Vous avez choisi xxx. Cliquez sur jouer";
				case Code.noGameChosen: return "S'il vous pla�t, choisissez le jeu!";
				case Code.loginAs: return "Connect� avec xxx.";
				case Code.sAuth: return "Authentification";
				case Code.sId: return "Identifiant";
				case Code.sPassword: return "Mot de passe";
				case Code.sCompleteAllField: return "Veuillez remplir tous les champs!";
				case Code.sLoginInvalide: return "Identifiant ou mot de passe invalide!";
				case Code.sMustLogged: return "Vous devez �tre connect� pour jouer au xxx!";
				case Code.error: return "Erreur..";
				case Code.firstIsSafe: return "La premi�re case est s�r..";
				case Code.cbProgressBar: return "Barre de chargement";
				case Code.mnuOption: return "Options";
				case Code.cbSizeCinq: return "5x5";
				case Code.cbSizeSix: return "6x6";
				case Code.cbSizeSept: return "7x7";
				case Code.cbSizeHuit: return "8x8";
				case Code.cbSizeNeuf: return "9x9";
				case Code.mnuInfo: return "Information";
				case Code.btnPause: return "Pause";
				case Code.btnCancel: return "Annuler";

			}
		case DE:
			switch (code) {
				case Code.btnStart: return "Anfang";
				case Code.btnRestart: return "Neustart";
				case Code.btnStop: return "Halt";
				case Code.btnClose: return "Schlie�en";
				case Code.mnuDiff: return "Schwierigkeit";
				case Code.rbEasy: return "Einfach";
				case Code.rbNormal: return "Normal";
				case Code.rbHard: return "Hart";
				case Code.mnuSize: return "Gr��e";
				case Code.rbRandom: return "Zuf�llig";
				case Code.mnuColor: return "Farbe";
				case Code.rbDefault: return "Standard";
				case Code.rbCustom: return "Freiw�hlbare";
				case Code.mnuLanguage: return "Sprache";
				case Code.mniImport: return "Einf�hren";
				case Code.mniExport: return "Export";
				case Code.mnuConfig: return "Aufbau";
				case Code.cbRefreshNumber: return "Nummer aktualisieren";
				case Code.cbTimer: return "Timer sichtbar";
				case Code.sAlreadyStart: return "Das Spiel ist bereits gestartet, klicken Sie auf Neustart";
				case Code.sGameNotStart: return "Das Spiel startet nicht, klicken Sie auf Start";
				case Code.sAlreadyStop: return "Das Spiel ist schon beendet";
				case Code.sDiffChange: return "Der Schwierigkeitsgrad wurde ge�ndert in xxx. Klicken Sie auf Start";
				case Code.sSizeChange: return "Der Gr��e wurde ge�ndert in xxx. Klicken Sie auf Start";
				case Code.sGameOver: return "Ende, verloren! Sie haben xxx/xxx Felder ber�hrt";
				case Code.sGameWin: return "Ende, Gewonnen! Du hast alle Kisten getroffen! (xxx)";
				case Code.sTimerVisible: return "Der Timer ist sichtbar";
				case Code.sTimePlayed: return "Spielzeit: xxx Sekunden";
				case Code.sTitlePortal: return "Gate";
				case Code.btnPlay: return "Abspielen";
				case Code.sLogin: return "Anmeldung";
				case Code.gameDeminer: return "Entminer";
				case Code.gameBBTAN: return "BBTAN";
				case Code.notLogin: return "Du bist nicht eingeloggt!";
				case Code.gameChosen: return "Du hast xxx gew�hlt. Klicke Sie auf Spielen";
				case Code.noGameChosen: return "Bitte w�hle das Spiel!";
				case Code.loginAs: return "Verbunden als xxx.";
				case Code.sAuth: return "Beglaubigung";
				case Code.sId: return "Identifizieren";
				case Code.sPassword: return "Passwort";
				case Code.sCompleteAllField: return "Bitte f�llen Sie alle Felder aus!!";
				case Code.sLoginInvalide: return "Ung�ltiger Benutzername oder ung�ltiges Passwort!";
				case Code.sMustLogged: return "Du musst eingeloggt sein, um xxx zu spielen!";
				case Code.error: return "Fehler..";
				case Code.firstIsSafe: return "Der erste Fall ist sicher..";
				case Code.cbProgressBar: return "Ladebalken";
				case Code.mnuOption: return "Optionen";
				case Code.cbSizeCinq: return "5x5";
				case Code.cbSizeSix: return "6x6";
				case Code.cbSizeSept: return "7x7";
				case Code.cbSizeHuit: return "8x8";
				case Code.cbSizeNeuf: return "9x9";
				case Code.mnuInfo: return "Informationen";
				case Code.btnPause: return "Pause";
				case Code.btnCancel: return "Stornieren";

			}
		default:
			return code;
		}
	}
	
}
