package com.github.purexo.umarkdown.ihm.panel;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JMenuBar;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;

import com.github.purexo.umarkdown.main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.io.File;

public class PnlMarkdown extends JPanel {
	private JTextField strFileMd;
	private JTextField strFileHTML;

	private File fEntree;
	private File fSortie;

	/**
	 * Create the panel.
	 */
	public PnlMarkdown() {
		setLayout(new GridLayout(0, 2, 0, 0));
		
		strFileMd = new JTextField();
		strFileMd.setColumns(10);
		strFileHTML = new JTextField();
		strFileHTML.setColumns(10);
		
		/*
		 * Bouton pour selectionner le fichier d'entree
		 */
		JButton btnFileMd = new JButton("Browse Markdown File");
		btnFileMd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        int returnVal = chooser.showOpenDialog(chooser.getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	fEntree = chooser.getSelectedFile();
		        	strFileMd.setText(chooser.getSelectedFile().getAbsolutePath());
		        }
			}
		});
		
		/*
		 * Bouton pour selectionner le fichier de sortie
		 */
		JButton btnFileHTML = new JButton("Browse HTML File");
		btnFileHTML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
		        int returnVal = chooser.showOpenDialog(chooser.getParent());
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		        	fSortie = chooser.getSelectedFile();
		        	strFileHTML.setText(chooser.getSelectedFile().getAbsolutePath());
		        }
			}
		});

		/*
		 * Bouton lancer la procedure
		 */
		JButton btnValidate = new JButton("Valider");
		btnValidate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fEntree = new File(strFileMd.getText());
				
				if (strFileHTML.getText().length() == 0)
					fSortie = new File(Main.autoName(fEntree));
				else
					fSortie = new File(strFileHTML.getText());
				
				try { Main.fileToHTML(fEntree, fSortie); }
				catch (Exception e1) { e1.printStackTrace(); }
			}
		});
		
		Box horizontalBox = Box.createHorizontalBox();

		add(strFileMd);
		add(btnFileMd);
		add(strFileHTML);
		add(btnFileHTML);
		add(horizontalBox);
		add(btnValidate);

	}

}
