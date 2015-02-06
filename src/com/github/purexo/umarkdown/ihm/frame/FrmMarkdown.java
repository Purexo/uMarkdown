package com.github.purexo.umarkdown.ihm.frame;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.purexo.umarkdown.ihm.panel.PnlMarkdown;

public class FrmMarkdown extends JFrame {
	private static final long serialVersionUID = 3558124465192390135L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FrmMarkdown() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		PnlMarkdown panel = new PnlMarkdown();
		contentPane.add(panel, BorderLayout.CENTER);
		
		setVisible(true);
	}

}
