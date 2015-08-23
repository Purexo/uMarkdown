package com.github.purexo.umarkdown.main;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import org.markdown4j.Markdown4jProcessor;

public class MarkdownHTML {

	/**
	 * @param fEntree : un fichier texte rédigé en Markdown
	 * @return String : le texte au format HTML
	 * @throws Exception : Signale que l'adresse est incorrecte
	 */
	public static String mdToHTML(File fEntree) throws Exception { // public pour être appelable depuis l'interface graphique
		/* */
		if (!fEntree.exists())
			throw new Exception("2.0: Le fichier Mardown est inéxistant");
		
		if (!fEntree.canRead())
			throw new Exception("2.1: Vous n'avez pas les permissions pour lire le fichier");
		
		if (!fEntree.isFile())
			throw new Exception("2.2: l'URI du document fourni n'est pas un fichier");
		/* */
		return new Markdown4jProcessor().process(fEntree);
	}
	
	/**
	 * Permet de transformer la chaine de caractère java.md en java.html
	 * @param f un Fichier
	 * @return le nom du fichier - l'extension + ".html"
	 */
	public static String autoName(File f) { // public pour etre appelable depuis l'interface graphique
		String name = f.getName ();
		int i = name.lastIndexOf (".");
		if ( i != -1 ) name = name.substring(0, i);
		
		return f.getParent() + "/" + name + ".html";
	}

	public static void fileToHTML(File fEntree, File fSortie) throws Exception { // public pour etre appelable depuis l'interface graphique
		
		if (fSortie.exists() && !fSortie.canWrite())
			throw new Exception("3: Vous n'avez pas la permission d'écriture sur ce ficher : " + fSortie.getName());
		
		String html = mdToHTML(fEntree); // peut couper le programme (throw exception n°2)
		
		FileWriter fwSortie = new FileWriter(fSortie);
		
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
	
	public static void fileToHTML(File fEntree, File fSortie, String pathtpl) throws Exception { // public pour etre appelable depuis l'interface graphique

		if (fSortie.exists() && !fSortie.canWrite())
			throw new Exception("3: Vous n'avez pas la permission d'écriture sur ce ficher : " + fSortie.getName());
		
		String html = mdToHTML(fEntree); // peut couper le programme (throw exception n°2)
		html = templating(pathtpl, html);
		
		FileWriter fwSortie = new FileWriter(fSortie);
		
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
	
	private static String templating(String pathtpl, String html) throws Exception {
		Scanner scanner = new Scanner( new File(pathtpl) );
		String tpl = scanner.useDelimiter("\\A").next();
		scanner.close();

		html = tpl.replace("{{MARKDOWN}}", html);
		return html;
	}
}
