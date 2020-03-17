package app.game.deminer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

import app.commun.Code;
import app.commun.Difficulty;
import app.commun.Translate;

@SuppressWarnings("serial")
public class ctrlDeminer extends JFrame {

	private Deminer pnlGame;
	private JMenu mnDifficulty;
	private JMenu mnSize;
	
	public ctrlDeminer(Translate translate, JFrame frmMenu) {		
		frmMenu.setEnabled(false);
		frmMenu.setVisible(false);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 435);
		
		setTitle("Riva Fabrice - " + translate.getMessage(Code.gameDeminer));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnDifficulty = new JMenu(translate.getMessage(Code.mnuDiff));
		menuBar.add(mnDifficulty);
		
		for (Difficulty diff : Difficulty.values()) {
			JRadioButtonMenuItem rbActu = new JRadioButtonMenuItem();
			rbActu.setAction(new actChangeDifficulty(diff.getName(translate), rbActu, diff));
			if (diff == Deminer.defaultDiff) {
				rbActu.setSelected(true);				
			}
			mnDifficulty.add(rbActu);
		}
		
		JCheckBoxMenuItem rbRefreshNumber = new JCheckBoxMenuItem();
		rbRefreshNumber.setAction(new AbstractAction(translate.getMessage(Code.cbRefreshNumber)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlGame.setRefreshNumber(rbRefreshNumber.isSelected());
			}
		});
		rbRefreshNumber.setSelected(true);
		mnDifficulty.add(rbRefreshNumber);
		
		JCheckBoxMenuItem cbSafeFirstCase = new JCheckBoxMenuItem(translate.getMessage(Code.firstIsSafe));
		cbSafeFirstCase.setSelected(true);
		mnDifficulty.add(cbSafeFirstCase);
		
		mnSize = new JMenu(translate.getMessage(Code.mnuSize));
		menuBar.add(mnSize);
		
		for (Size size : Size.values()) {
			JRadioButtonMenuItem rbActu = new JRadioButtonMenuItem();
			rbActu.setAction(new actChangeSize(size.getName(translate), rbActu, size));
			if (size == Deminer.defaultSize) {
				rbActu.setSelected(true);				
			}
			mnSize.add(rbActu);
		}
		
		JMenu mnColor = new JMenu(translate.getMessage(Code.mnuColor));
		menuBar.add(mnColor);
		
		JRadioButtonMenuItem rdbtnmntmDefault = new JRadioButtonMenuItem();
		rdbtnmntmDefault.setAction(new AbstractAction(translate.getMessage(Code.rbDefault)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < mnColor.getItemCount(); i++) {
					JRadioButtonMenuItem rbActu = (JRadioButtonMenuItem) mnColor.getItem(i);
					rbActu.setSelected(false);
				}
				JColor.setDefault();
			}
		});
		rdbtnmntmDefault.setSelected(true);
		mnColor.add(rdbtnmntmDefault);
		
		JRadioButtonMenuItem rdbtnmntmCustom = new JRadioButtonMenuItem();
		rdbtnmntmCustom.setAction(new AbstractAction(translate.getMessage(Code.rbCustom)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnmntmDefault.setSelected(false);
				rdbtnmntmCustom.setSelected(true);
				
				setVisible(false);
				frmColor couleur = new frmColor(ctrlDeminer.this);
				couleur.setVisible(true);
				
				pnlGame.stop();
			}
		});
		mnColor.add(rdbtnmntmCustom);
		
		JMenu mnConfig = new JMenu(translate.getMessage(Code.mnuConfig));
		menuBar.add(mnConfig);
		
		JMenu mnInfo = new JMenu(translate.getMessage(Code.mnuInfo));
		menuBar.add(mnInfo);
		
		JCheckBoxMenuItem cbTimer = new JCheckBoxMenuItem();
		cbTimer.setSelected(true);
		cbTimer.setAction(new AbstractAction(translate.getMessage(Code.cbTimer)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlGame.setTimerEnable(cbTimer.isSelected());
			}
		});
		mnInfo.add(cbTimer);
		
		JMenuItem mntmStats = new JMenuItem();
		mntmStats.setAction(new AbstractAction("Stats") {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mnInfo.add(mntmStats);
		
		JPanel pnlMain = new JPanel();
		
		pnlMain.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlMain.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlMain);
		
		JPanel pnlButtons = new JPanel();
		pnlMain.add(pnlButtons, BorderLayout.SOUTH);
		
		JButton btnStart = new JButton();
		btnStart.setAction(new AbstractAction(translate.getMessage(Code.btnStart)) {
			public void actionPerformed(ActionEvent e) {
				pnlGame.start();
			}
		});
		pnlButtons.add(btnStart);
		
		JButton btnRestart = new JButton();
		btnRestart.setAction(new AbstractAction(translate.getMessage(Code.btnRestart)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlGame.restart();
			}
		});
		pnlButtons.add(btnRestart);
		
		JButton btnArrter = new JButton();
		btnArrter.setAction(new AbstractAction(translate.getMessage(Code.btnStop)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlGame.stop();
			}
		});
		pnlButtons.add(btnArrter);
		
		JButton btnExit = new JButton();
		btnExit.setAction(new AbstractAction(translate.getMessage(Code.btnClose)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				frmMenu.setEnabled(true);
				frmMenu.setVisible(true);
			}
		});
		pnlButtons.add(btnExit);
		
		pnlMain.add(pnlGame = new Deminer(translate), BorderLayout.CENTER);
		
		this.setLocation(frmMenu.getLocation());
		this.setVisible(true);
	}
	
	public static Boolean isLoggedRequire() {
		return true;
	}

	private class actChangeDifficulty extends AbstractAction {
		private JRadioButtonMenuItem rb;
		private Difficulty diff;
		public actChangeDifficulty(String name, JRadioButtonMenuItem rb, Difficulty diff) {
			this.rb = rb;
			this.diff = diff;
			putValue(NAME, name);
		}
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < mnDifficulty.getItemCount(); i++) {
				try {
					JRadioButtonMenuItem rbActu = (JRadioButtonMenuItem)mnDifficulty.getItem(i);
					rbActu.setSelected(false);
				} catch (Exception e2) {}
			}
			rb.setSelected(true);
			pnlGame.changeDifficulty(diff);
		}
	}
	
	private class actChangeSize extends AbstractAction {
		private JRadioButtonMenuItem rb;
		private Size size;
		public actChangeSize(String name, JRadioButtonMenuItem rb, Size size) {
			this.rb = rb;
			this.size = size;
			putValue(NAME, name);
		}
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < mnSize.getItemCount(); i++) {
				try {
					JRadioButtonMenuItem rbActu = (JRadioButtonMenuItem)mnSize.getItem(i);
					rbActu.setSelected(false);
				} catch (Exception e2) {}
			}
			rb.setSelected(true);
			pnlGame.changeSize(size);
		}
	}
}
