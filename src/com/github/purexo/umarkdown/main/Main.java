package com.github.purexo.umarkdown.main;
import java.io.File;
import java.io.FileWriter;

import org.markdown4j.Markdown4jProcessor;

import com.github.purexo.umarkdown.ihm.frame.FrmMarkdown;

public class Main {
	/**
	 * Entrées :
	 * 	args[0] : Adresse du fichier markdown
	 * 	args[1] : Adresse de sortie du fichier html
	 * @param args
	 * @throws Exception
	 * 	0: pas d'erreur \n
	 * 	1: pas assez de parametres \n
	 * 	2: Adresse du fichier markdown incorrecte \n
	 * 	3: Adresse du fichier html incorrecte
	 */
	
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		String[] targs = {
			"G:\\Travail\\Perso\\Java\\µmarkdown\\test\\test.md"
			// ,"C:\\Users\\Théotime\\Desktop\\test.html"
		};
		File fEntree;
		File fSortie;
		try {
			switch (args.length) {
				case 0:
					@SuppressWarnings("unused")
					FrmMarkdown framePrg = new FrmMarkdown();
					break;
					// throw new Exception("1: nb de parametres insufisant (GUI non implanté)");
				case 1:
					fEntree = new File(args[0]);
					fSortie = new File(MarkdownHTML.autoName(fEntree));
					MarkdownHTML.fileToHTML(fEntree, fSortie);
					break;
				default:
					fEntree = new File(args[0]);
					fSortie = new File(args[1]);
					MarkdownHTML.fileToHTML(fEntree, fSortie);
					break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
