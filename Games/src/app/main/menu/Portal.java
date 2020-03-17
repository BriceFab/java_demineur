package app.main.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import app.commun.Code;
import app.commun.Lang;
import app.commun.Translate;
import app.game.bbtan.ctrlBbtan;
import app.game.deminer.ctrlDeminer;

public class Portal {

	private JFrame frmMenu = new JFrame();
	private JMenu mnLang = new JMenu();
	private JList<String> lstGames = new JList<>();
	private limGame modelGame = new limGame();
	private JLabel lblChoosegame = new JLabel();
	private JLabel lblLoginas = new JLabel();
	private Translate translate = new Translate();
	
	private Boolean isLogged = false;
	private String username;
	
	private Action actClose;
	private Action actPlay;
	private Action actPause;
	private Action actCancel;
	private Action actLogin;
	private Action actProgressBar;
	private JProgressBar barLaunch = new JProgressBar();
	private final Timer tmrLaunch = new Timer(500, (ActionListener) null);
	private Boolean bProgressBar = true;
	private JButton btnLogin = new JButton();
	private JButton btnJouer = new JButton();

	public Portal() {
		initialize();
		
		frmMenu.setVisible(true);
	}

	@SuppressWarnings("serial")
	private void initialize() {
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlAction = new JPanel();
		frmMenu.getContentPane().add(pnlAction, BorderLayout.SOUTH);
		
		JPanel pnlMenu = new JPanel();
		frmMenu.getContentPane().add(pnlMenu, BorderLayout.CENTER);
		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[]{0, 0};
		gbl_pnlMenu.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pnlMenu.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnlMenu.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlMenu.setLayout(gbl_pnlMenu);
		
		barLaunch.setStringPainted(true);
		barLaunch.setVisible(false);
		
		GridBagConstraints gbc_lblLoginas = new GridBagConstraints();
		gbc_lblLoginas.insets = new Insets(0, 0, 5, 0);
		gbc_lblLoginas.gridx = 0;
		gbc_lblLoginas.gridy = 0;
		pnlMenu.add(lblLoginas, gbc_lblLoginas);
		
		GridBagConstraints gbc_lblChoosegame = new GridBagConstraints();
		gbc_lblChoosegame.insets = new Insets(0, 0, 5, 0);
		gbc_lblChoosegame.gridx = 0;
		gbc_lblChoosegame.gridy = 1;
		pnlMenu.add(lblChoosegame, gbc_lblChoosegame);
		
		lstGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_lstGames = new GridBagConstraints();
		gbc_lstGames.insets = new Insets(0, 0, 5, 0);
		gbc_lstGames.gridx = 0;
		gbc_lstGames.gridy = 2;
		gbc_lstGames.fill = GridBagConstraints.BOTH;
		pnlMenu.add(lstGames, gbc_lstGames);
		GridBagConstraints gbc_barLaunch = new GridBagConstraints();
		gbc_barLaunch.gridx = 0;
		gbc_barLaunch.gridy = 3;
		pnlMenu.add(barLaunch, gbc_barLaunch);
		lstGames.setModel(modelGame);
		lstGames.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					changeSelectedGame(lblChoosegame);
					actPlay.setEnabled(true);	
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmMenu.setJMenuBar(menuBar);
		
		menuBar.add(mnLang);
		
		JMenu mnOption = new JMenu(translate.getMessage(Code.mnuOption));
		menuBar.add(mnOption);
		
		JCheckBoxMenuItem chckbxmntmProgressBar = new JCheckBoxMenuItem();
		chckbxmntmProgressBar.setSelected(true);
		chckbxmntmProgressBar.setAction(actProgressBar = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bProgressBar = chckbxmntmProgressBar.isSelected();
			}
		});
		mnOption.add(chckbxmntmProgressBar);
		
		for (Lang lang : Lang.values()) {
			JRadioButtonMenuItem mnrb = new JRadioButtonMenuItem();
			mnrb.setAction(new actChangeLang(lang, mnrb));
			mnLang.add(mnrb);
			if (lang == translate.getLang()) {
				mnrb.setSelected(true);
			}
		}
		
		btnJouer.setAction(actPlay = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				launch(Game.getGame(lstGames.getSelectedValue(), translate));
			}
		});
		pnlAction.add(btnJouer);		
		
		btnLogin.setAction(actLogin = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onActionLogin();
			}
		});
		pnlAction.add(btnLogin);
		
		JButton btnClose = new JButton();
		btnClose.setAction(actClose = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pnlAction.add(btnClose);
		
		actPause = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (actPause.getValue(NAME).equals(translate.getMessage(Code.btnPause))) {
					tmrLaunch.stop();
					actPause.putValue(NAME, translate.getMessage(Code.btnRestart));
				} else if (actPause.getValue(NAME).equals(translate.getMessage(Code.btnRestart))) {
					tmrLaunch.start();
					actPause.putValue(NAME, translate.getMessage(Code.btnPause));
				}
			}
		};
		
		actCancel = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				beforeLaunch();
			}
		};
		
		if (isLogged) {
			actLogin.setEnabled(false);
		}
		actPlay.setEnabled(false);
		actPause.setEnabled(false);
		actCancel.setEnabled(false);
		lblLoginas.setForeground(Color.RED);
		barLaunch.setMaximum(100);
		barLaunch.setMinimum(0);
		
		RefreshText();
	}
	
	private class tmrAction implements ActionListener {
		private Game g;
		private final int pas = 10;

		public tmrAction(Game g) {
			this.g = g;
			
			tmrLaunch.start();
			actPlay.setEnabled(false);
			lstGames.setEnabled(false);
			actLogin.setEnabled(false);
			actProgressBar.setEnabled(false);
			barLaunch.setVisible(true);
			actPause.setEnabled(true);
			actCancel.setEnabled(true);
			btnJouer.setAction(actPause);
			btnLogin.setAction(actCancel);
		}
		
		public void actionPerformed(ActionEvent e) {
			barLaunch.setValue(barLaunch.getValue() + pas);
			if (barLaunch.getValue() == barLaunch.getMaximum()) {
				launchGame(g);
				
				beforeLaunch();
			}
		}
	}
	
	private void beforeLaunch() {
		tmrLaunch.stop();
		actPlay.setEnabled(true);
		lstGames.setEnabled(true);
		actLogin.setEnabled(true);
		actProgressBar.setEnabled(true);
		barLaunch.setValue(0);
		barLaunch.setVisible(false);
		actPause.setEnabled(false);
		btnJouer.setAction(actPlay);
		actCancel.setEnabled(false);
		btnLogin.setAction(actLogin);
	}
	
	private void launch(Game g) {		
		if (isLogged || !Game.isLoggedRequire(g)) {
			if (bProgressBar && Game.haveProgressBar(g)) {
				tmrLaunch.addActionListener(new tmrAction(g));						
			} else {
				launchGame(g);
			}
		} else {
			if (JOptionPane.showConfirmDialog(frmMenu,
					translate.getMessage(Code.sMustLogged, g.getName(translate)),
					translate.getMessage(Code.error),
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE)
					== JOptionPane.OK_OPTION) {
				onActionLogin();
			}
		}
	}
	
	private void launchGame(Game g) {
		switch (g) {
		case DEMINER:
				new ctrlDeminer(translate, frmMenu);
			break;
		case BBTAN:
				new ctrlBbtan(translate, frmMenu);
			break;
		default:
			break;
		}
	}
	
	private void onActionLogin() {
		new Login(frmMenu, this, translate);
		RefreshText();
	}
	
	public void setLogged(Boolean bLogged) {
		this.isLogged = bLogged;
		actLogin.setEnabled(!isLogged);
		if (isLogged) {
			lblLoginas.setForeground(Color.GREEN);
		}
	}
	
	public void setUsername(String username) {
		this.username = username;
		RefreshText();
	}
	
	@SuppressWarnings("serial")
	private class actChangeLang extends AbstractAction {
		private JRadioButtonMenuItem rbSelected;
		private Lang lang;
		public actChangeLang(Lang lang, JRadioButtonMenuItem rbSelected) {
			putValue(NAME, lang.name());
			this.rbSelected = rbSelected;
			this.lang = lang;
		}
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < mnLang.getItemCount(); i++) {
				JRadioButtonMenuItem actLang = (JRadioButtonMenuItem) mnLang.getItem(i);
				actLang.setSelected(false);
			}
			rbSelected.setSelected(true);
			translate.changeLang(lang);
			
			RefreshText();
		}
	}
	
	private void changeSelectedGame(JLabel lblGame) {
		lblGame.setText(translate.getMessage(Code.gameChosen, lstGames.getSelectedValue()));
	}
	
	private void RefreshText() {
		frmMenu.setTitle("Riva Fabrice - " + translate.getMessage(Code.sTitlePortal));
		mnLang.setText(translate.getMessage(Code.mnuLanguage));
		actClose.putValue(AbstractAction.NAME, translate.getMessage(Code.btnClose));
		actPlay.putValue(AbstractAction.NAME, translate.getMessage(Code.btnPlay));
		actLogin.putValue(AbstractAction.NAME, translate.getMessage(Code.sLogin));
		modelGame.changeLang(translate);
		lblLoginas.setText(isLogged ? translate.getMessage(Code.loginAs, username) : translate.getMessage(Code.notLogin));
		if (lstGames.isSelectionEmpty()) {
			lblChoosegame.setText(translate.getMessage(Code.noGameChosen));			
		} else {
			changeSelectedGame(lblChoosegame);
		}
		actProgressBar.putValue(AbstractAction.NAME, translate.getMessage(Code.cbProgressBar));
		actPause.putValue(AbstractAction.NAME, translate.getMessage(Code.btnPause));
		actCancel.putValue(AbstractAction.NAME, translate.getMessage(Code.btnCancel));
	}
}
