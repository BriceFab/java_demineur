package app.game.deminer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import app.commun.Code;
import app.commun.Difficulty;
import app.commun.Environment;
import app.commun.Translate;

@SuppressWarnings("serial")
public class Deminer extends JPanel {
	public static final Difficulty defaultDiff = Difficulty.EASY;
	public static final Size defaultSize = Size.Random;
	public static final Environment env = Environment.TEST;
	
	private Case[][] tblCase;
	private Boolean isPlaying;
	private Boolean isGameOver;
	private Boolean isEnding;
	private Boolean bFirstIsSafe;
	private Boolean refreshNumber;
	private Difficulty gameDifficulty;
	private Size size;
	private int iCaseClicked;
	private int nbrMines;
	private Timer tmr;
	private JLabel lblInfo;
	private JLabel lblTime;
	private JPanel pnlCases;
	private final lTimer actTimer;
	private Translate translate;

	public Deminer(Translate translate) {
		this(defaultDiff, defaultSize, translate);
	}
	
	public Deminer(Difficulty diff, Size size, Translate translate) {
		this.gameDifficulty = diff;
		this.size = size;
		this.translate = translate;
		
		refreshNumber = true;
		bFirstIsSafe = true;
		tmr = new Timer(1000, actTimer = new lTimer());
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		this.setLayout(gridBagLayout);
		
		lblInfo = new JLabel();
		GridBagConstraints gbc_lblLbltimer = new GridBagConstraints();
		gbc_lblLbltimer.insets = new Insets(0, 0, 5, 0);
		gbc_lblLbltimer.gridx = 0;
		gbc_lblLbltimer.gridy = 0;
		this.add(lblInfo, gbc_lblLbltimer);
		
		lblTime = new JLabel();
		GridBagConstraints gbc_lblLblinfo = new GridBagConstraints();
		gbc_lblLblinfo.insets = new Insets(0, 0, 5, 0);
		gbc_lblLblinfo.gridx = 0;
		gbc_lblLblinfo.gridy = 1;
		this.add(lblTime, gbc_lblLblinfo);
		
		pnlCases = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		this.add(pnlCases, gbc_panel);
		
		init();
	}
	
	private void init() {
		tblCase = null;
		isPlaying = false;
		isGameOver = false;
		isEnding = false;
		iCaseClicked = 0;
		nbrMines = 0;
		
		actTimer.iTime = 1;
		pnlCases.removeAll();
		pnlCases.setBackground(JColor.cGameBackground);
		lblInfo.setText("");
		lblTime.setText("");
		this.setBackground(JColor.cGameBackground);
		lblInfo.setForeground(JColor.cInfo);
		
		revalidate();
		repaint();
	}
	
	public void start() {
		if (!isPlaying) {
			init();
			
			tmr.start();
			isPlaying = true;
			
			drawPanelCase();
		} else {
			lblInfo.setText(translate.getMessage(Code.sAlreadyStart));
		}
	}
	
	public void restart() {
		if (isPlaying) {
			stop();
			start();
		} else {
			lblInfo.setText(translate.getMessage(Code.sGameNotStart));
		}
	}
	
	public void stop() {
		if (isPlaying) {
			
			tmr.stop();
			isPlaying = false;
			
			init();
		} else {
			lblInfo.setText(translate.getMessage(Code.sAlreadyStop));
		}
	}
	
	public void changeDifficulty(Difficulty diff) {
		this.gameDifficulty = diff;
		if (isPlaying) {
			stop();
		}
		lblInfo.setText(translate.getMessage(Code.sDiffChange, diff.name()));
	}
	
	public void changeSize(Size size) {
		this.size = size;
		if (isPlaying) {
			stop();
		}
		lblInfo.setText(translate.getMessage(Code.sSizeChange, size.name()));
	}
	
	private void drawPanelCase() {
		Random rdm = new Random();
		final int iMin = 5;
		final int iMax = 10;
		
		int iNbrCaseLigne = 0;
		int iNbrCaseColonne = 0;
		
		int nbrSize = Size.getNumber(size);
		if (nbrSize != 0) {
			iNbrCaseLigne = iNbrCaseColonne = nbrSize;
		} else {
			iNbrCaseLigne = iNbrCaseColonne = rdm.nextInt(iMax - iMin) + iMin;
		}
		
		JPanel pnlDraw = new JPanel();
		pnlDraw.setBackground(JColor.cGameBackground);
		pnlDraw.setBorder(BorderFactory.createLineBorder(JColor.cInfo, 1, true));
		
		final int dimCase = 25;
		
		tblCase = new Case[iNbrCaseLigne][iNbrCaseColonne];
		for (int i = 0; i < iNbrCaseLigne; i++) {
			for (int j = 0; j < iNbrCaseColonne; j++) {
				Case c = new Case(dimCase);
				tblCase[i][j] = c;
				pnlDraw.add(c);
			}
		}
		
		final int iEspacement = 6;
		Dimension dimPnlCase = new Dimension(iNbrCaseColonne * (dimCase + iEspacement), iNbrCaseLigne * (dimCase + iEspacement));
		pnlDraw.setPreferredSize(dimPnlCase);
		pnlDraw.setMaximumSize(dimPnlCase);
		pnlDraw.setMinimumSize(dimPnlCase);
		
		pnlCases.add(pnlDraw);
	}
	
	public void setGameOver() {
		isGameOver = true;
		isEnding = true;
		
		lblInfo.setText(translate.getMessage(Code.sGameOver, new String[] { iCaseClicked + "", (tblCase[0].length * tblCase[1].length) - nbrMines + "" }));

		for (int i = 0; i < tblCase[0].length; i++) {
			for (int j = 0; j < tblCase[1].length; j++) {
				tblCase[i][j].onAction();
			}
		}
		
		tmr.stop();
	}
	
	public void setGameWin() {
		isEnding = true;
		
		lblInfo.setText(translate.getMessage(Code.sGameWin, iCaseClicked + ""));
		
		tmr.stop();
	}
	
	public void setRefreshNumber(Boolean b) {
		refreshNumber = b;
	}
	
	public void setTimerEnable(Boolean b) {
		if (!b) {
			lblTime.setVisible(false);
		} else {
			lblTime.setVisible(true);
			lblInfo.setText(translate.getMessage(Code.sTimerVisible));
		}
	}
	
	private class Case extends JPanel implements MouseListener {
		private Color color;
		private Dimension dim;
		private Boolean isMine;
		private Boolean isClicked;
		private JLabel lblNumber;
		
		public Case(int dimCase) {
			color = JColor.cCaseHide;
			isClicked = false;
			lblNumber = new JLabel("");
			
			Random rdm = new Random();
			final int iMin = 1;
			int iMax = Difficulty.getRatio(app.main.menu.Game.DEMINER, gameDifficulty) + iMin;
			isMine = (rdm.nextInt(iMax - iMin) + iMin == iMax - iMin);

			if (isMine) {
				nbrMines++;
			}
			
			dim = new Dimension(dimCase, dimCase);
			setPreferredSize(dim);
			setMaximumSize(dim);
			setMinimumSize(dim);
			add(lblNumber);
			addMouseListener(this);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
					
			g.setColor(color);
			g.fillRect(0, 0, dim.width, dim.height);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (!isGameOver && isPlaying && !isEnding) {				
				lblInfo.setText("");
				onAction();
				
				if (refreshNumber) {
					refreshNumber();	
				}
				
				if (!isMine) {
					lblNumber.setText(getNbrSafeCaseProximity(getPosTab((Case)e.getSource())) + "");
				}
			}
		}
		
		private void refreshNumber() {
			for (int i = 0; i < tblCase[0].length; i++) {
				for (int j = 0; j < tblCase[1].length; j++) {
					if (tblCase[i][j].isClicked) {
						tblCase[i][j].lblNumber.setText(getNbrSafeCaseProximity(getPosTab(tblCase[i][j]))+"");
					}
				}
			}
		}
		
		private int getNbrSafeCaseProximity(Point posCase) {
			final Point gMin = new Point(0, 0);
			final Point gMax = new Point((size != Size.Random) ? Size.getNumber(size) : tblCase[0].length, (size != Size.Random) ? Size.getNumber(size) : tblCase[1].length);
			gMax.x = gMax.x-1;
			gMax.y = gMax.y-1;
			Point pMin = new Point(posCase);
			Point pMax = new Point(posCase);
			
			if (pMin.x > gMin.x) {
				pMin.x--;
			}
			if (pMin.y > gMin.y) {
				pMin.y--;
			}
			if (pMax.x < gMax.x) {
				pMax.x++;
			}
			if (pMax.y < gMax.y) {
				pMax.y++;
			}

			int nbrSafeCase = 0;
			for (int x = pMin.x; x <= pMax.x; x++) {
				for (int y = pMin.y; y <= pMax.y; y++) {
					Case caseActu = tblCase[x][y];
					if (caseActu != tblCase[posCase.x][posCase.y]) {
						if (!caseActu.isMine && !caseActu.isClicked) {
							nbrSafeCase++;
						}
					}
				}
			}
			
			return nbrSafeCase;
		}
		
		private Point getPosTab(Case c) {
			for (int i = 0; i < tblCase[0].length; i++) {
				for (int j = 0; j < tblCase[1].length; j++) {
					if (tblCase[i][j] == c) {
						return new Point(i, j);
					}
				}
			}
			return null;
		}
		
		public void onAction() {
			if (!isClicked && isPlaying) {
				
				if (!isMine) {
					color = JColor.cCase;
					iCaseClicked++;
					if (iCaseClicked == (tblCase[0].length * tblCase[1].length) - nbrMines) {
						if (!isGameOver) {
							setGameWin();
						}
					}
				} else {
					if (bFirstIsSafe && iCaseClicked == 0) {
						color = JColor.cCase;
						iCaseClicked++;
						isMine = false;
						nbrMines--;
					} else {
						color = JColor.cMine;
						if (!isGameOver) {
							setGameOver();
						}	
					}
				}
				
				if (!isGameOver) {
					isClicked = true;
					setBorder(BorderFactory.createLineBorder(JColor.cCaseSelected, 1));	
				}
				
				repaint();	
			}
		}

		@Override public void mouseEntered(MouseEvent e) {}

		@Override public void mouseExited(MouseEvent e) {}

		@Override public void mousePressed(MouseEvent e) {}

		@Override public void mouseReleased(MouseEvent e) {}
	}
	
	private class lTimer implements ActionListener {
		public int iTime = 1;
		@Override
		public void actionPerformed(ActionEvent e) {
			lblTime.setText(translate.getMessage(Code.sTimePlayed, iTime++ + ""));				
		}
	}

}
