package app.game.bbtan;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import app.commun.Code;
import app.commun.Translate;

@SuppressWarnings("serial")
public class ctrlBbtan extends JFrame {
	private Bbtan pnlGame;

	public static Boolean isLoggedRequire() {
		return false;
	}
	
	public ctrlBbtan(Translate translate, JFrame frmMenu) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 610);
		setTitle("Riva Fabrice - " + translate.getMessage(Code.gameBBTAN));
		
		JMenuBar mnuMain = new JMenuBar();
		setJMenuBar(mnuMain);
		
		JMenu mnDifficulty = new JMenu("Difficulty");
		mnuMain.add(mnDifficulty);
		
		JMenu mnColor = new JMenu("Color");
		mnuMain.add(mnColor);
		
		frmMenu.setVisible(false);
		frmMenu.setEnabled(false);
		
		JPanel pnlAction = new JPanel();
		pnlAction.setBackground(SystemColor.controlHighlight);
		getContentPane().add(pnlAction, BorderLayout.SOUTH);
		
		JButton btnStart = new JButton();
		btnStart.setAction(new AbstractAction(translate.getMessage(Code.btnStart)) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlGame.start();
			}
		});
		pnlAction.add(btnStart);
		
		JButton btnClose = new JButton();
		btnClose.setAction(new AbstractAction(translate.getMessage(Code.btnClose)) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				frmMenu.setEnabled(true);
				frmMenu.setVisible(true);
			}
		});
		pnlAction.add(btnClose);
		
		pnlGame = new Bbtan(translate);
		getContentPane().add(pnlGame, BorderLayout.CENTER);
		
		this.setLocation(frmMenu.getLocation());
		this.setVisible(true);
	}
}
