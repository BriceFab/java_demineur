package app.game.bbtan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import app.commun.Difficulty;
import app.commun.Environment;
import app.commun.Translate;

@SuppressWarnings("serial")
public class Bbtan extends JPanel {
	public static final Environment env = Environment.DEV;
	
	private Boolean isPlaying;
	private Translate translate;
	private Difficulty difficulty;
	private Bloc[][] tblBloc;
	private Bloc[][] tblBlocVide;
	private final int maxRow = 11;
	private final int maxColumn = 9;
	private Boolean bShowLine;
	private Point p2Line;
	private Ball ballFix;
	
	private Timer tmrBall;
	private Boolean bBallMove;
	private Ball ballMove;
	
	public Bbtan(Translate translate, Difficulty difficulty) {
		this.translate = translate;
		this.difficulty = difficulty;
		init();
	}
	
	public Bbtan(Translate translate) {
		this(translate, Difficulty.EASY);
	}
	
	public void start() {
		if (!isPlaying) {
			isPlaying = true;
			drawRow();
		} else {
			System.out.println("Already start");
		}
	}
	
	private void init() {		
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(Bloc.espacement, Bloc.espacement, Bloc.espacement, Bloc.espacement));
		
		ballFix = new Ball(Color.WHITE);
		GridBagConstraints gbConst = new GridBagConstraints();
		gbConst.gridwidth = 3;
		gbConst.insets = new Insets(0, 0, 5, 5);
		gbConst.gridx = maxColumn / 2 - 1;
		gbConst.gridy = maxRow + 1;
		this.add(ballFix, gbConst);

		tblBloc = new Bloc[maxRow][maxColumn];
		isPlaying = false;
		bShowLine = false;
		bBallMove = false;
		
		tblBlocVide = new Bloc[maxRow][maxColumn];
		for (int l = 0; l < maxRow; l++) {
			for (int c = 0; c < maxColumn; c++) {
				Bloc b = new Bloc(0, this.getBackground());
				tblBlocVide[l][c] = b;
				this.add(b, getConstraint(l, c));
			}
		}
		
		this.addMouseListener(new actListener());
	}
	
	private class actListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			
			Point pDest = new Point(e.getX(), e.getY());
			if (e.getButton() == MouseEvent.BUTTON1) {
				launchBall(pDest);
			} else {
				drawLine(pDest);
			}			
		}
	}
	
	private void launchBall(Point pDest) {
		bShowLine = false;
		repaint();
		System.out.println("launch ball");
		
		bBallMove = true;
		tmrBall = new Timer(1000, new actTimer(ballFix, pDest));
		tmrBall.start();
	}
	
	private class actTimer implements ActionListener {
		private Point pDest;
		private final int pas = 2;
		private Point pActu;
		
		public actTimer(Ball ballFix, Point pDest) {
			ballMove = new Ball(ballFix.getColor(), new Point(ballFix.getX() + Ball.dimBall / 2, ballFix.getY() + Ball.dimBall / 2));
			pActu = new Point(ballMove.getPos().x, ballMove.getPos().x);
			this.pDest = pDest;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pActu.x = pActu.x + pas;
			pActu.y = pActu.y + pas;
			if (pActu.x <= pDest.x || pActu.y <= pDest.y) {
				ballMove.move(pActu);
				repaint();
			} else {
				System.out.println("arrivé a destination");
				tmrBall.stop();
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (bShowLine) {
			g.setColor(Color.WHITE);
			g.drawLine(ballFix.getX() + Ball.dimBall / 2, ballFix.getY() + Ball.dimBall / 2, p2Line.x, p2Line.y);
		}
		
		if (bBallMove && ballMove != null) {
			g.setColor(ballMove.getColor());
			g.drawOval(ballMove.getPos().x, ballMove.getPos().y, Ball.dimBall, Ball.dimBall);
			System.out.println("here: " + ballMove.getPos());
		}
	}
	
	private class Ball extends JPanel {
		public static final int dimBall = 20;
		private Dimension dim;
		private Color color;
		private Point posBall;
		
		public Ball(Color color, Point posBall) {
			this.dim = new Dimension(dimBall, dimBall);
			this.color = color;
			this.posBall = posBall;
			this.setPreferredSize(dim);
			this.setMinimumSize(dim);
			this.setMaximumSize(dim);
		}
		
		public Ball(Color color) {
			this(color, new Point(0, 0));
		}
		
		public Color getColor() {
			return this.color;
		}
		
		public Point getPos() {
			return this.posBall;
		}
		
		public void move(Point posBall) {
			this.posBall = posBall;
			repaint();
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(color);
			g.fillOval(posBall.x, posBall.y, dim.width, dim.height);
		}
	}
	
	private void drawLine(Point pDest) {
		bShowLine = true;
		p2Line = pDest;
		repaint();
	}
	
	private void drawRow() {
		final int numberRow = getRowNumber();
		if (numberRow != -1) {
			int duraMin = 0;
			int duraMax = 0;
			int maxRow = 0;
			int nbrOnRow = 0;
			Random rdm = new Random();
			switch (difficulty) {
			case EASY:
					duraMin = 5;
					duraMax = 80;
					maxRow = 6;
				break;
			case NORMAL:
					duraMin = 10;
					duraMax = 150;
					maxRow = 7;
				break;
			case HARD:
					duraMin = 20;
					duraMax = 200;
					maxRow = 9;
				break;
			}
			
			final int minRow = 1;
			nbrOnRow = rdm.nextInt(maxRow - minRow) + minRow;
			
			for (int i = 0; i < nbrOnRow; i++) {
				int dura = rdm.nextInt((duraMax) - duraMin) + duraMin;
				Bloc b = new Bloc(dura, Color.RED); //TODO Color
				
				ArrayList<Integer> empVide = new ArrayList<>();
				for (int c = 0; c < this.maxColumn; c++) {
					if (tblBloc[numberRow][c] == null) {
						empVide.add(c);
					}
				}
				
				if (empVide.size() > 0) {
					int column = empVide.get(rdm.nextInt(empVide.size()));
					tblBloc[numberRow][column] = b;
					
					this.remove(tblBlocVide[numberRow][column]);
					this.add(b, getConstraint(numberRow, column));
				} else {
					i = nbrOnRow;
				}
			}
			
			this.revalidate();
			this.repaint();
		} else {
			System.out.println("Game over");
		}
	}
	
	private int getRowNumber() {
		Boolean cont = true;
		while (cont) {
			for (int l = 0; l < maxRow; l++) {
				int nbrBloc = 0;
				for (int c = 0; c < maxColumn; c++) {
					if (tblBloc[l][c] != null) {
						nbrBloc++;
					}
				}
				if (nbrBloc <= 0) {
					cont = false;
					return l;
				}
			}
			cont = false;
		}
		return -1;
	}
	
	private GridBagConstraints getConstraint(int row, int column) {
		GridBagConstraints gbConst = new GridBagConstraints();
		gbConst.insets = new Insets(Bloc.espacement, Bloc.espacement, Bloc.espacement, Bloc.espacement);
		gbConst.gridy = row;
		gbConst.gridx = column;
		return gbConst;
	}
	
	private class Bloc extends JPanel {
		public static final int espacement = 8;
		public static final int dimBloc = 29;
		private int dura;
		private Dimension dim;
		private JLabel lblDura;
		
		public Bloc(int dura, Color color) {
			this.dura = dura;
			this.dim = new Dimension(dimBloc, dimBloc);
			this.setPreferredSize(this.dim);
			this.setMaximumSize(this.dim);
			this.setMinimumSize(this.dim);
			this.setBackground(color);
			lblDura = new JLabel();
			add(lblDura);
			updateDura();
		}
		
		private void updateDura() {
			if (dura > 0) {
				lblDura.setText(dura + "");
			}
		}
	}

}
