package app.game.deminer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class frmColor extends JFrame {

	private final ctrlDeminer frmMain;
	
	//TODO Use Translate et code
	public frmColor(ctrlDeminer frmMain) {
		this.frmMain = frmMain;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 229);
		setTitle("Custom color");
		setResizable(false);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		final int iDim = 40;
		Dimension dim = new Dimension(iDim, iDim);
				
		JLabel lblNewLabel = new JLabel("Mine:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel);
		
		JPanel pnlMine = new JPanel();
		pnlMine.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlMine.setPreferredSize(dim);
		pnlMine.setBackground(JColor.cMine);
		pnlMine.addMouseListener(new lColorChooser(pnlMine));
		panel.add(pnlMine);
		
		JLabel lblNewLabel_1 = new JLabel("Info text:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNewLabel_1);
		
		JPanel pnlInfoText = new JPanel();
		pnlInfoText.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlInfoText.setPreferredSize(dim);
		pnlInfoText.setBackground(JColor.cInfo);
		pnlInfoText.addMouseListener(new lColorChooser(pnlInfoText));
		panel.add(pnlInfoText);
		
		JLabel lblBackground = new JLabel("Background:");
		lblBackground.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblBackground);
		
		JPanel pnlBackground = new JPanel();
		pnlBackground.setBackground(JColor.cGameBackground);
		pnlBackground.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlBackground.setPreferredSize(dim);
		pnlBackground.addMouseListener(new lColorChooser(pnlBackground));
		panel.add(pnlBackground);
		
		JLabel lblCaseHide = new JLabel("Case hide:");
		lblCaseHide.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCaseHide);
		
		JPanel pnlCaseHide = new JPanel();
		pnlCaseHide.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlCaseHide.setPreferredSize(dim);
		pnlCaseHide.setBackground(JColor.cCaseHide);
		pnlCaseHide.addMouseListener(new lColorChooser(pnlCaseHide));
		panel.add(pnlCaseHide);
		
		JLabel lblSafeCase = new JLabel("Safe case:");
		lblSafeCase.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblSafeCase);
		
		JPanel pnlSafeCase = new JPanel();
		pnlSafeCase.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlSafeCase.setPreferredSize(dim);
		pnlSafeCase.setBackground(JColor.cCase);
		pnlSafeCase.addMouseListener(new lColorChooser(pnlSafeCase));
		panel.add(pnlSafeCase);
		
		JLabel lblCaseSelected = new JLabel("Case selected:");
		lblCaseSelected.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCaseSelected);
		
		JPanel pnlCaseSelected = new JPanel();
		pnlCaseSelected.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pnlCaseSelected.setPreferredSize(dim);
		pnlCaseSelected.setBackground(JColor.cCaseSelected);
		pnlCaseSelected.addMouseListener(new lColorChooser(pnlCaseSelected));
		panel.add(pnlCaseSelected);
		
		JPanel pnlBas = new JPanel();
		contentPane.add(pnlBas, BorderLayout.SOUTH);
		
		JButton btnSave = new JButton("Save");
		btnSave.setAction(new AbstractAction("Save") {
			@Override
			public void actionPerformed(ActionEvent e) {
				JColor.cGameBackground = pnlBackground.getBackground();
				JColor.cCaseHide = pnlCaseHide.getBackground();
				JColor.cCaseSelected = pnlCaseSelected.getBackground();
				JColor.cInfo = pnlInfoText.getBackground();
				JColor.cCase = pnlSafeCase.getBackground();
				JColor.cMine = pnlMine.getBackground();

				Close();
			}
		});
		pnlBas.add(btnSave);
		
		JButton btnAnnuler = new JButton("Cancel");
		btnAnnuler.setAction(new AbstractAction("Cancel") {
			@Override
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});
		pnlBas.add(btnAnnuler);
	}
	
	private class lColorChooser implements MouseListener {
		private JPanel pnlColor;
		
		public lColorChooser(JPanel pnlColor) {
			this.pnlColor = pnlColor;
		}
		
		@Override public void mouseClicked(MouseEvent e) {
			Color color = JColorChooser.showDialog(pnlColor, "Choissez une nouvelle couleur", pnlColor.getBackground());
			if (color != null) {
				pnlColor.setBackground(color);
			}
		}

		@Override public void mouseEntered(MouseEvent e) {}

		@Override public void mouseExited(MouseEvent e) {}

		@Override public void mousePressed(MouseEvent e) {}

		@Override public void mouseReleased(MouseEvent e) {}
	}
	
	private void Close() {
		frmMain.setVisible(true);
		dispose();
	}
}
