package com.github.purexo.umarkdown.main;

import java.io.File;

import com.github.purexo.umarkdown.ihm.frame.FrmMarkdown;

public class Process {
	private boolean gui = true;			// utilise t'on la GUI ?
	private boolean site = false;		// utilise t'on le mode "Site Static" ? (non implanté)
	private boolean page = true;		// utilise t'on le mode "page" ?
	
	private String in = "";				// chemin du fichier d'entré (Markdown)
	private String out = "";			// chemin du fichier de sortie (HTML)
	
	private String tpl = "Ressources/tpl/default/tpl.html";	// chemin du fichier de template
	
	public Process(String[] params) {
		for (String param : params) {
			if (param == "-nogui")			gui  = false;
			else if (param == "-gui")		gui  = true;
			
			else if (param == "-site")		site = true;
			else if (param == "-nosite") 	site = false;
			else if (param == "-page") 		page = true;
			
			else if (param.startsWith("in="))	in  = param.replaceAll("in=|\"", "");
			
			else if (param.startsWith("out=")) 	out = param.replaceAll("out=|\"", "");

			else if (param.startsWith("tpl=")) 	tpl = param.replaceAll("tpl=|\"", "");
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

		fEntree = new File(in);
		fSortie = (out == "") ? new File(MarkdownHTML.autoName(fEntree)) : new File(out);
		
		if (page)
			MarkdownHTML.fileToHTML(fEntree, fSortie, tpl);
		else
			MarkdownHTML.fileToHTML(fEntree, fSortie);
	}
}
