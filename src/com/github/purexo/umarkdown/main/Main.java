package com.github.purexo.umarkdown.main;
import java.io.File;
import java.io.FileWriter;

import org.markdown4j.Markdown4jProcessor;

public class Main {
	/**
	 * Entr�es :
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
		String html;
		
		String[] targs = {"C:\\Users\\Th�otime\\Desktop\\test.md", "C:\\Users\\Th�otime\\Desktop\\test.html"};
		
		if (args.length < 2) throw new Exception("1: nb de parametres inssufisant");
		
		File fEntree = new File(args[0]);
		File fSortie = new File(args[1]);
		FileWriter fwSortie = new FileWriter(fSortie);
		
		if (fEntree.exists() && fEntree.canRead() && fEntree.isFile())
			html = new Markdown4jProcessor().process(fEntree);
		else {
			fwSortie.close();
			throw new Exception("2: Adresse du fichier markdown incorrecte");
		}
		
		if (fSortie.canWrite()) {
			fSortie.createNewFile(); 									// Creer un nouveau fichier au besoin
			fwSortie.flush();											// Le nettoie avant �criture
			fwSortie.write(html);
			fwSortie.close();
		} else {
			fwSortie.close();
			throw new Exception("3: Adresse du fichier html incorrecte");
		}
		
		String success = "" +
		"Votre fichier " + fEntree.getName() + "\n" +
		"� �t� Pars� vers \n" +
		fSortie.getAbsolutePath() + "\n" +
		"Avec Succes.";
		
		System.out.println(success);
	}
	
	/*
	 * Prevue pour version future
	 * possibilit� de reduire � 1 le nb de parametres (param d'entree)
	 * 	creer et nommer automatiquement le fichier de sortie en fonction du fichier d'entree
	 *  dans le meme dossier, avec .html en plus
	 */

}
