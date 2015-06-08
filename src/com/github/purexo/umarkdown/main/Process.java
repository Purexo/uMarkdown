package com.github.purexo.umarkdown.main;

import java.io.File;

import com.github.purexo.umarkdown.ihm.frame.FrmMarkdown;

public class Process {
	private boolean gui = true;			// utilise t'on la GUI ?
	private boolean site = false;		// utilise t'on le mode "Site Static" ? (non implanté)
	
	private String in = "";				// chemin du fichier d'entré (Markdown)
	private String out = "";			// chemin du fichier de sortie (HTML)
	
	private String tpl = "";			// chemin du fichier de template
	private String vct = "";			// chemin du fichier var container ("à sérialiser")
	
	public Process(String[] params) {
		for (int i = 0; i < params.length; i++) {
			String param = params[i];

			if 		(param == "-nogui") 	gui  = false;
			else if (param == "-gui") 		gui  = true;
			else if (param == "-site") 		site = true;
			else if (param == "-nosite") 	site = false;
			
			else if (param.startsWith("in="))	in  = param.replaceFirst("in=", "");
			else if (param.startsWith("out=")) 	out = param.replaceFirst("out=", "");

			else if (param.startsWith("tpl=")) 	tpl = param.replaceFirst("tpl=", "");
			else if (param.startsWith("vct=")) 	vct = param.replaceFirst("vct=", "");
		}
		
		try {
			if (gui) launchGUI();
			else launchCMD();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void launchGUI() {
		FrmMarkdown framePrg = new FrmMarkdown();
		framePrg.setFileIn(in);
		framePrg.setFileOut(out);
	}
	
	private void launchCMD() throws Exception {
		if (in == "") throw new Exception("2. : Le fichier markdown n'a pas été précisé");
		
		File fEntree;
		File fSortie;
		
		fEntree = new File(in);
		fSortie = (out == "") ? new File(MarkdownHTML.autoName(fEntree)) : new File(out);
		
		MarkdownHTML.fileToHTML(fEntree, fSortie);
	}
}
