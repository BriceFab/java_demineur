package app.main.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import app.commun.Code;
import app.commun.Translate;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane = new JPanel();
	private JLabel lblError = new JLabel();
	private JPasswordField txtPassword = new JPasswordField();;
	private JTextField txtIdentifiant = new JTextField();;
	private final JFrame frmPortal;
	private final Portal portal;

	public Login(JFrame frmPortal, Portal portal, Translate translate) {
		this.frmPortal = frmPortal;
		this.portal = portal;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 306, 229);
		setLocation(frmPortal.getLocation());
		setTitle(translate.getMessage(Code.sLogin));
		frmPortal.setEnabled(false);
		frmPortal.setVisible(false);
		setVisible(true);
		setResizable(false);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlHaut = new JPanel();
		contentPane.add(pnlHaut, BorderLayout.NORTH);
		pnlHaut.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblGestionDeLogin = new JLabel(translate.getMessage(Code.sAuth));
		lblGestionDeLogin.setFont(new Font("Vani", Font.BOLD, 24));
		pnlHaut.add(lblGestionDeLogin);
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BoxLayout(pnlCenter, BoxLayout.Y_AXIS));
		
		lblError.setForeground(new Color(255, 0, 0));
		pnlCenter.add(lblError);
		
		JLabel lblIdentifiant = new JLabel(translate.getMessage(Code.sId));
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCenter.add(lblIdentifiant);
		
		pnlCenter.add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
		
		JLabel lblPassword = new JLabel(translate.getMessage(Code.sPassword));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlCenter.add(lblPassword);
		
		pnlCenter.add(txtPassword);
		
		JPanel pnlBas = new JPanel();
		contentPane.add(pnlBas, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setAction(new AbstractAction(translate.getMessage(Code.sLogin)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				
				String sLogin = txtIdentifiant.getText();
				String sPassword = "";
				for (char c : txtPassword.getPassword()) {
					sPassword += c;
				}
				
				if (sLogin.length() <= 0 || sPassword.length() <= 0) {
					lblError.setText(translate.getMessage(Code.sCompleteAllField));
					return;
				}
				
				if (sLogin.equals(Auth.login) && sPassword.equals(Auth.password)) {
					Close(true);
				} else {
					lblError.setText(translate.getMessage(Code.sLoginInvalide));
				}
			}
		});
		pnlBas.add(btnNewButton);
		
		JButton btnAnnuler = new JButton();
		btnAnnuler.setAction(new AbstractAction(translate.getMessage(Code.btnClose)) {
			@Override
			public void actionPerformed(ActionEvent e) {
				Close(false);
			}
		});
		pnlBas.add(btnAnnuler);
	}
	private void Close(Boolean bLogged) {
		this.portal.setLogged(bLogged);
		if (bLogged) {
			this.portal.setUsername(this.txtIdentifiant.getText());
		}
		frmPortal.setVisible(true);
		frmPortal.setEnabled(true);
		
		this.setVisible(false);
		this.setEnabled(false);
		dispose();
	}
}
