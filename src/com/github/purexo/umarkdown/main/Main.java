package com.github.purexo.umarkdown.main;

public class Main {
	/**
	 * @param args : Tableau des parammetres :
	 * 	-gui : active l'interface utilisateur
	 * 	-nogui : programme mode console
	 * 	-page : utilise le mode "page" (permet d'utiliser un template pour ajouter de l'html et du css)
	 * 	-site : parcourera recursivement le dossier d'entré et crééra pour chaque fichier markdown, un fichier html
	 * 	-in="path/to/folder/file" : fichier ou dossier d'entré à parser
	 * 	-out="path/to/folder/file" : fichier ou dossier de sortie
	 * @throws Exception
	 * 	0: pas d'erreur 
	 * 	1: pas assez de parametres
	 * 	2: Adresse du fichier markdown incorrecte
	 * 	3: Adresse du fichier html incorrecte
	 */
	
	public static void main(String[] args) throws Exception {
		String[] targs = {
			"-gui", "-page"
			,"in=\"D:\\Projets\\Java\\uMarkdown\\test\\test.md\""
			,"out=\"C:\\Users\\Théotime\\Desktop\\test.html\""
		};

		new Process(targs);
	}

}
