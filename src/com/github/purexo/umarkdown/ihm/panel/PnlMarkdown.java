package com.github.purexo.umarkdown.ihm.panel;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import com.github.purexo.umarkdown.main.Main;
import com.github.purexo.umarkdown.main.MarkdownHTML;


public class PnlMarkdown extends JPanel {
	private static final long serialVersionUID = -6751670620071347727L;
	
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
		btnFileMd.addActionListener(ALBtnMd);
		
		/*
		 * Bouton pour selectionner le fichier de sortie
		 */
		JButton btnFileHTML = new JButton("Browse HTML File");
		btnFileHTML.addActionListener(ALBtnHTML);

		/*
		 * Bouton lancer la procedure
		 */
		JButton btnValidate = new JButton("Run");
		btnValidate.addActionListener(ALBtnOK);
		
		Box horizontalBox = Box.createHorizontalBox();

		add(strFileMd);
		add(btnFileMd);
		add(strFileHTML);
		add(btnFileHTML);
		add(horizontalBox);
		add(btnValidate);

	}

	/* --- ActionListener --- */
	private ActionListener ALBtnMd = new ActionListener() { // bouton Markdown
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Markdown files", "md", "markdown");
		    chooser.setFileFilter(filter);

	        int returnVal = chooser.showOpenDialog(chooser.getParent());
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	fEntree = chooser.getSelectedFile();
	        	strFileMd.setText(chooser.getSelectedFile().getAbsolutePath());
	        }
		}
	};
	private ActionListener ALBtnHTML = new ActionListener() { // bouton HTML
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "HTML files", "html", "htm", "xhtml");
		    chooser.setFileFilter(filter);
		    
	        int returnVal = chooser.showOpenDialog(chooser.getParent());
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	fSortie = chooser.getSelectedFile();
	        	strFileHTML.setText(chooser.getSelectedFile().getAbsolutePath());
	        }
		}
	};
	private ActionListener ALBtnOK = new ActionListener() { // bouton de validation
		public void actionPerformed(ActionEvent event) {
			fEntree = new File(strFileMd.getText());
			
			if (strFileHTML.getText().length() == 0)
				fSortie = new File(MarkdownHTML.autoName(fEntree));
			else
				fSortie = new File(strFileHTML.getText());
			
			try { MarkdownHTML.fileToHTML(fEntree, fSortie); }
			catch (Exception e) { e.printStackTrace(); }
		}
	};
	
}
