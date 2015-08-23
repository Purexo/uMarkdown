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
		strFileTPL = new JTextField();
		strFileTPL.setColumns(10);
		
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
		 * Bouton pour sélectionner le fichier de template
		 */
		JButton btnFileTPL = new JButton("Browse Template File");
		btnFileTPL.addActionListener(ALBtnTPL);

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
		add(strFileTPL);
		add(btnFileTPL);
		
		add(horizontalBox);
		add(btnValidate);

	}

	public void setFileIn(String file) {
		strFileMd.setText(file);
	}
	public void setFileOut(String file) {
		strFileHTML.setText(file);
	}

	/* --- ActionListener --- */
	private ActionListener ALBtnMd = new ActionListener() { // button  Markdown
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
	private ActionListener ALBtnHTML = new ActionListener() { // button HTML
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
	private ActionListener ALBtnTPL = new ActionListener() { // button TPL
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "HTML files", "html", "htm", "xhtml", "tpl");
		    chooser.setFileFilter(filter);
		    
	        int returnVal = chooser.showOpenDialog(chooser.getParent());
	        if(returnVal == JFileChooser.APPROVE_OPTION) {
	        	fSortie = chooser.getSelectedFile();
	        	strFileTPL.setText(chooser.getSelectedFile().getAbsolutePath());
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
			
			try {
				if (strFileTPL.getText().length() == 0)
					MarkdownHTML.fileToHTML(fEntree, fSortie);
				else
					MarkdownHTML.fileToHTML(fEntree, fSortie, strFileTPL.getText());
			}
			catch (Exception e) { e.printStackTrace(); }
		}
	};
	private JTextField strFileTPL;
	
}
