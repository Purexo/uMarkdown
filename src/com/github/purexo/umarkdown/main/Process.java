package com.github.purexo.umarkdown.main;

import java.io.File;

import com.github.purexo.umarkdown.ihm.frame.FrmMarkdown;

public class Process {
	private boolean gui = true;			// utilise t'on la GUI ?
	private boolean site = false;		// utilise t'on le mode "Site Static" ? (non implanté)
	private boolean page = true;		// utilise t'on le mode "page" ? (non implanté)
	
	private String in = "";				// chemin du fichier d'entré (Markdown)
	private String out = "";			// chemin du fichier de sortie (HTML)
	
	private String tpl = "Ressources/tpl/default/tpl.html";	// chemin du fichier de template
	private String vct = "Ressources/tpl/default/vct.html";	// chemin du fichier var container ("à sérialiser")
	//private boolean usertpl = false;	// template fournis par l'user ?
	//private boolean uservct = false;	// var container fournis par l'user ?
	
	public Process(String[] params) {
		for (int i = 0; i < params.length; i++) {
			String param = params[i];

			if (param == "-nogui")			gui  = false;
			else if (param == "-gui")		gui  = true;
			
			else if (param == "-site")		site = true;
			else if (param == "-nosite") 	site = false;
			else if (param == "-page") 		page = true;
			
			//else if (param == "-usertpl") 	usertpl = true;
			//else if (param == "-uservct") 	uservct = true;
			
			else if (param.startsWith("in="))	in  = param.replaceAll("in=|\"", "");
			
			else if (param.startsWith("out=")) 	out = param.replaceAll("out=|\"", "");

			else if (param.startsWith("tpl=")) 	tpl = param.replaceAll("tpl=|\"", "");
			else if (param.startsWith("vct=")) 	vct = param.replaceAll("vct=|\"", "");
		}
		
		try {
			if (gui) launchGUI();
			else launchCMD();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void launchGUI() {
		FrmMarkdown framePrg = new FrmMarkdown();
		framePrg.setFileIn(in);
		framePrg.setFileOut(out);
	}
	
	private void launchCMD() throws Exception {
		if (in == "") throw new Exception("2. : Le fichier markdown n'a pas été précisé");
		System.out.println(in);
		
		File fEntree;
		File fSortie;

		//fEntree = new File("D:\\SavClé\\Travail\\Perso\\Java\\µmarkdown\\test.md");
		fEntree = new File(in);
		fSortie = (out == "") ? new File(MarkdownHTML.autoName(fEntree)) : new File(out);
		
		if (page)
			MarkdownHTML.fileToHTML(fEntree, fSortie, tpl, vct);
		else
			MarkdownHTML.fileToHTML(fEntree, fSortie);
	}
}
