package com.github.purexo.umarkdown.main;

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
			"-nogui", "-page",
			"in=\"D:\\SavClé\\Travail\\Perso\\Java\\µmarkdown\\test.md\""
			// ,"C:\\Users\\Théotime\\Desktop\\test.html"
		};

		new Process(targs);
	}

}
