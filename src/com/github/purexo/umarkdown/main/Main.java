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
		
		switch (args.length) {
			case 0:
				@SuppressWarnings("unused")
				FrmMarkdown framePrg = new FrmMarkdown();
				break;
				// throw new Exception("1: nb de parametres insufisant (GUI non implanté)");
			case 1:
				fEntree = new File(args[0]);
				fSortie = new File(autoName(fEntree));
				fileToHTML(fEntree, fSortie);
				break;
			default:
				fEntree = new File(args[0]);
				fSortie = new File(args[1]);
				fileToHTML(fEntree, fSortie);
				break;
		}
		
	}
	
	/**
	 * 
	 * @param fEntree
	 * @return
	 * @throws Exception
	 */
	public static String mdToHTML(File fEntree) throws Exception { // public pour etre appelable depuis l'interface graphique
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
	public static String autoName(File f) { // public pour etre appelable depuis l'interface graphique
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
	
	public static void fileToHTML(File fEntree, File fSortie) throws Exception { // public pour etre appelable depuis l'interface graphique
		String userInclude = 
			"<link rel=\"stylesheet\" href=\"//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/styles/default.min.css\">" + "\n\t" +
			"<script src=\"//cdnjs.cloudflare.com/ajax/libs/highlight.js/8.4/highlight.min.js\"></script>";
		userInclude = "";
		String html =
			"<!DOCTYPE HTML>\n<html>\n<head>\n" +
			"\t<meta http-equiv=\"Content-Type\" content=\"text/html\" />" + "\n" +
			"\t<title>" + fSortie.getName() + "</title>" + "\n" +
			"\t" + userInclude + "\n" +
			"</head>" + "\n" +
			"<body>" + "\n" +
			mdToHTML(fEntree) + // peut couper le programme (throw exception n°2)
			"</body>\n</html>";
		
		FileWriter fwSortie = new FileWriter(fSortie);

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

}
