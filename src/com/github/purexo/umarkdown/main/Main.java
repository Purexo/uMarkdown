package com.github.purexo.umarkdown.main;

public class Main {
	/**
	 * @param args : Tableau des parammetres : <br />
	 * 	-gui : active l'interface utilisateur <br />
	 * 	-nogui : programme mode console <br />
	 * 	-page : utilise le mode "page" (permet d'utiliser un template pour ajouter de l'html et du css) <br />
	 * 	-site : parcourera recursivement le dossier d'entré et crééra pour chaque fichier markdown, un fichier html <br />
	 * 	-in="path/to/folder/file" : Fichier ou dossier d'entré à parser <br />
	 * 	-out="path/to/folder/file" : Fichier ou dossier de sortie <br />
	 * 	-tpl="path/to/tpl" : Fichier de template
	 * @throws Exception
	 * 	0: pas d'erreur <br />
	 * 	1: pas assez de parametres <br />
	 * 	2: Adresse du fichier markdown incorrecte <br />
	 * 	3: Adresse du fichier html incorrecte
	 */
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		String[] targs = {
			"-gui", "-page"
			,"in=\"D:\\Projets\\Java\\uMarkdown\\test\\test.md\""
			,"out=\"C:\\Users\\Théotime\\Desktop\\test.html\""
		};

		new Process(args);
	}

}
