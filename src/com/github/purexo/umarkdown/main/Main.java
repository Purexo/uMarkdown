package com.github.purexo.umarkdown.main;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.markdown4j.Markdown4jProcessor;

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
		String[] targs = {
			"G:\\Travail\\Perso\\Java\\µmarkdown\\test\\test.md"
			// ,"C:\\Users\\Théotime\\Desktop\\test.html"
		};
		File fEntree;
		File fSortie;
		FileWriter fwSortie;
		String html;
		
		switch (args.length) {
			case 0:
				throw new Exception("1: nb de parametres inssufisant (GUI non implanté)");
			case 1:
				fEntree = new File(args[0]);
				fSortie = new File(autoName(fEntree));
				break;
			default:
				fEntree = new File(args[0]);
				fSortie = new File(args[1]);
				break;
				
		}

		html = mdToHTML(fEntree); // peut couper le programme (throw exception n°2)
		fwSortie = new FileWriter(fSortie);

		if (!fSortie.canWrite()) {
			fwSortie.close();
			throw new Exception("3: Adresse du fichier html incorrecte");
		}
		fSortie.createNewFile(); 									// Creer un nouveau fichier au besoin
		fwSortie.flush();											// Le nettoie avant écriture
		fwSortie.write(html);
		fwSortie.close();

		String success = "" +
		"Votre fichier " + fEntree.getName() + "\n" +
		"à été Parsé vers \n" +
		fSortie.getAbsolutePath() + "\n" +
		"Avec Succes.";
		
		System.out.println(success);
	}
	
	/**
	 * 
	 * @param fEntree
	 * @return
	 * @throws Exception
	 */
	private static String mdToHTML(File fEntree) throws Exception {
		if (fEntree.exists() && fEntree.canRead() && fEntree.isFile())
			return new Markdown4jProcessor().process(fEntree);
		else {
			throw new Exception("2: Adresse du fichier markdown incorrecte");
		}
	}
	
	/**
	 * prend un fichier
	 * @param f un Fichier
	 * @return le nom du fichier - l'extension + ".html"
	 */
	private static String autoName(File f) {
		return f.getParent() + "/" + f.getName() + ".html";
		/*
		String[] tab = f.getName().split(".");
		String fName = "";
		
		for (int i = 0; i < tab.length - 1; i++)
			fName += tab[i];
		
		String str = fName + ".html";
		return str;
		*/
	}
	
	/*
	 * Prevue pour version future
	 * possibilité de reduire à 1 le nb de parametres (param d'entree)
	 * 	creer et nommer automatiquement le fichier de sortie en fonction du fichier d'entree
	 *  dans le meme dossier, avec .html en plus
	 */

}
