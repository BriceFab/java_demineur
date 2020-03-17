package app.main.start;

import java.awt.EventQueue;

import app.main.menu.Portal;

public class Start {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Portal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
