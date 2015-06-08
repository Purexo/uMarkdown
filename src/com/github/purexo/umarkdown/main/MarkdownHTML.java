package com.github.purexo.umarkdown.main;

import java.io.File;
import java.io.FileWriter;

import org.markdown4j.Markdown4jProcessor;

public class MarkdownHTML {

	/**
	 * @param fEntree : un fichier texte rédigé en Markdown
	 * @return String : le texte au format HTML
	 * @throws Exception : Signale que l'adresse est incorecte
	 */
	public static String mdToHTML(File fEntree) throws Exception { // public pour etre appelable depuis l'interface graphique
		if (!fEntree.exists())
			throw new Exception("2.0: Le fichier Mardown est inéxistant");
		
		if (!fEntree.canRead())
			throw new Exception("2.1: Vous n'avez pas les permissions pour lire le fichier");
		
		if (!fEntree.isFile())
			throw new Exception("2.2: l'URI du document fourni n'est pas un fichier");
		
		return new Markdown4jProcessor().process(fEntree);
	}
	
	/**
	 * Permet de transformer la chaine de caratère java.md en java.html
	 * @param f un Fichier
	 * @return le nom du fichier - l'extension + ".html"
	 */
	public static String autoName(File f) { // public pour etre appelable depuis l'interface graphique
		String[] split = f.getName().split(".");
		String name = "";
		
		if (split.length > 1) {
			for (int i = 0; i < split.length - 1; i++) {
				if (i == 0) name += split[i];
				else name += "." + split[i];
			}
		} else name = f.getName();
		
		return f.getParent() + "/" + name + ".html";
	}
	
	public static void fileToHTML(File fEntree, File fSortie) throws Exception { // public pour etre appelable depuis l'interface graphique
		if (!fEntree.exists())
			throw new Exception("2.0: Le fichier Mardown est inéxistant");
		if (!fEntree.canRead())
			throw new Exception("2.1: Vous n'avez pas les permissions pour lire le fichier");
		if (!fEntree.isFile())
			throw new Exception("2.2: l'URI du document fourni n'est pas un fichier");

		if (!fSortie.canWrite())
			throw new Exception("3: Vous n'avez pas la permission d'écriture sur ce ficher");
		
		FileWriter fwSortie = new FileWriter(fSortie);
		
		String html = mdToHTML(fEntree); // peut couper le programme (throw exception n°2)
		
		
		fSortie.createNewFile(); 									// Creer un nouveau fichier au besoin
		fwSortie.flush();											// Le nettoie avant écriture
		fwSortie.write(html);
		fwSortie.close();

		String success =
			"Votre fichier " + fEntree.getName() + "\n" +
			"à été Parsé vers " + fSortie.getAbsolutePath() + "\n" +
			"Avec Succes.";
		
		System.out.println(success);
	}
}
