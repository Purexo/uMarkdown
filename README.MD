# µMarkdown
Est un petit programme java sans pretention permettant de parser simplement un document markdown vers un document html

La librairie pour parser utilisé est [markdown4j](https://code.google.com/p/markdown4j/#Learning_Markdown_syntax).    
Suivez le lien ci-dessus pour découvrir les fonctionalités syntaxique supplémentaire de ce parseur.     
Ainsi que [celui-ci](http://daringfireball.net/projects/markdown/syntax) pour la doc officielle.

# Telechargement
[Les dernieres Releases](https://github.com/purexo/uMarkdown/releases)

# Utilisation
## Outil cli :
### Liste des paramètres
 * [X] -gui : active l'interface utilisateur
 * [X] -nogui : programme mode console
 * [X] -page : utilise le mode "page" (permet d'utiliser un template pour ajouter de l'html et du css)
 * [ ] -site : parcourera recursivement le dossier d'entré et crééra pour chaque fichier markdown, un fichier html
 * [X] -in="path/to/folder/file" : fichier ou dossier d'entré à parser
 * [X] -out="path/to/folder/file" : fichier ou dossier de sortie

``` bash
# Some launch examples
java -jar path/to/µMarkdown.jar -nogui in="path/to/your/file.md" out="path/to/your/file.html"
java -jar path/to/µMarkdown.jar -nogui -page in="path/to/your/file.md" out="path/to/your/file.html" tpl="path/to/template.html"
java -jar path/to/µMarkdown.jar -nogui in="path/to/your/file.md"
# The html will be at the same place with file.md.html as name
```

## Outil graphique :

``` bash
java -jar path/to/µMarkdown.jar #Vous aurez les logs
```
Ou simplement double clic sur le fichier .jar

## Fichier de template
Créez simplement un fichier HTML Complet, avec du js du css et tout le bazar si vous le souhaitez.
veillez simplement à ce que la chaine de caractère {{MARKDOWN}} soit présente quelque part dans votre fichier. Votre markdown remplacera cette chaine de caractère.

Les .jar sont des archives, vous pouvez ajouter des fichiers dedans. Donc vous pouvez embarquer vos template à l'interieur (dans le dossier /tpl de préférence ;-))

### Concernant la façon de donner l'URL de votre fichier de template :
Vous pouvez utiliser des URL relative ou absolue. le programme cherchera l'URL souhaité dans votre système. 2 Possibilité s'ouvrent alors :

1. Le fichier est présent dans votre système, le programme l'utilisera.
2. Le fichier n'est pas présent et ira regarder dans ses ressources.
	* Si vous souhaitez utiliser un template présent dans les ressources, il est conseillé d'utiliser une URL absolue : /tpl/default.html (c'est le template présent par défaut)

### Le fichier de template par défaut :
C'est un template assez simple qui va :

* assurer que la page est bien en UTF8.
* Utiliser la librairie HighlightJS pour coloriser le code (via cdn : plus simple dans un contexte logiciel)

# Licence
Creative Commons - BY - NC - SA    
En gros vous pouvez utiliser mon code (ainsi que le binaire) librement

* Tant que vous me citez 
* Tant que ce n'est pas a des fins commercial (sans mon consentement), si je code un truc pénar pour le difuser librement, c'est pas pour qu'on me le reprènne tel quel et le revendre, ou difuser le liens via des sites comme adf.ly ...
* Tant que vous restez sur la meme licence pour des raisons évidentes...

# Pour la suite :
### V1 :
1. [x] Un mode "automatique" seul le fichier markdown est a fournir, le fichier html seras généré dans le meme dossier que le fichier markdown avec le meme nom + ".html"</s> (done)
2. [x] Une interface graphique (simple) une case pour le fichier markdown (pouvant ouvrir une fenetre de navigation pour choisir le fichier). Une deuxieme case pour choisir la destination. et un bouton valider
3. [x] Faire un code un peu moins degeulasse (genre pas faire en sorte que le main() balance des exceptions ... xD)
4. [x] faire un systeme de parametre avancé du genre :

``` bash
java -jar µMarkdown -nogui in="path/to/file.md" out="path/to/file.html" -page tpl="/tpl/default.html"
```

---------------------------------------------------------------------------
### V2
1. [X] Personnaliser le rendu en integrant du style css dans l'html via le code (genre un template façon [github, page static](https://pages.github.com/) généré par du markdown)
	* Un fichier de template est fourni. Ressources/tpl/default/tpl.html
	* ça ne change pas le comportement du parser
	* en mettant la "balise" {{MARKDOWN}} dans votre fichier de template, celle-ci sera remplacé par votre markdown parsé.
2. [X] Rendre le petit 4 moddable, genre un dossier Templates dans le .jar ou les gens pourraient rajouter leurs modeles de page static.
3. [X] Interface graphique supportant le mode page/template intégré.

---------------------------------------------------------------------------
### V3
7. [ ] mode "site" : parcours un dossier recurssivement à la recherche des .md pour les convertir en html, tout en copiant collant les autres fichier

``` bash
java -jar µMarkdown -nogui in="path/to/mdFolder" out="path/to/htmlFolder" theme="Merlot"
```
