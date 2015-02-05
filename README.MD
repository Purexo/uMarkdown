# µMarkdown
Est un petit programme java sans pretention permettant de parser simplement un document markdown vers un document html

La librairie pour parser utilisé est [markdown4j](https://code.google.com/p/markdown4j/#Learning_Markdown_syntax).    
Suivez le lien ci-dessus pour découvrir les fonctionalités syntaxique supplémentaire de ce parseur.     
Ainsi que [celui-ci](http://daringfireball.net/projects/markdown/syntax) pour la doc officielle.

# Utilisation
Outil cli :    

``` bash
java -jar path/to/µMarkdown.jar "path/to/your/file.md" "path/to/your/file.html"
```

# Licence
Creative Commons - BY - NC - SA    
En gros vous pouvez utiliser mon code (ainsi que le binaire) librement

* Tant que vous me citez 
* Tant que ce n'est pas a des fins commercial (sans mon consentement), si je code un truc pénar pour le difuser librement, c'est pas pour qu'on me le reprènne tel quel et le revendre, ou difuser le liens via des sites comme adf.ly ...
* Tant que vous restez sur la meme licence pour des raisons évidentes...

# Pour la suite :
1. Un mode "automatique" seul le fichier markdown est a fournir, le fichier html seras généré dans le meme dossier que le fichier markdown avec le meme nom + ".html"
2. Une interface graphique (simple) une case pour le fichier markdown (pouvant ouvrir une fenetre de navigation pour choisir le fichier). Un deuxieme case pour choisir la destination. et un bouton valider
3. Faire un code un peu moins degeulasse (genre pas faire en sorte que le main() balance des exceptions ... xD)

---------------------------------------------------------------------------

4. Personnaliser le rendu en integrant du style css dans l'html via le code (genre un template façon [github, page static](https://pages.github.com/) généré par du markdown)
5. Rendre le petit 4 moddable, genre un dossier Templates dans le .jar ou les gens pourraient rajouter leurs modeles de page static.
6. faire un systeme de parametre avancé du genre :

``` bash
java -jar µMarkdown -nogui in="path/to/file.md" out="path/to/file.html" theme="Merlot"
```

---------------------------------------------------------------------------

7. mode "site" : parcours un dossier recurssivement à la recherche des .md pour les convertir en html, tout en copiant collant les autres fichier

``` bash
java -jar µMarkdown -nogui in="path/to/mdFolder" out="path/to/htmlFolder" theme="Merlot"
```