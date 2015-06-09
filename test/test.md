Bonjour, je vous fournis un petit tuto pour installer et configurer une seedbox simplement sur votre serveur.

Mon système est basé sur transmission-daemon/transmission-remote et apache2 + h5ai (pour la beauté). Je pars du principe que votre apache est installé et configuré correctement, et que vous avez les bases dans la configuration d'apache, notamment sur les VirtualHost.

## Prérequis
- Debian 8
- Apache >= 2.4

Sinon il y aura quelques configurations à adapter ;-)

## Installation
```bash
sudo apt-get install transmission-daemon
sudo service transmission-daemon stop
```
## Configuration
### Transmission
Ensuite vous pourrez éditer le fichier de configuration (assurez vous à chaque fois que vous éditerez le fichier, que transmission-daemon sois stoppé) : `/etc/transmission-daemon/settings.json`. Voici les paramètres à modifier :

```JSON
{
    "download-dir": "/var/seedbox",
    "incomplete-dir": "/var/seedbox/.incomplete", 
    "incomplete-dir-enabled": true, 
    "rpc-username": "VotreUserName", 
    "rpc-password": "VotreMDPEnCLair", // quand le service démarrera il chiffrera votre mot de passe
    "rpc-whitelist-enabled": false, 
    "umask": 22, // pour que apache soit pas emmerdé plus tard
}
```

### Apache
```bash
sudo mkdir /var/seedbox /var/seedbox/.incomplete

# instalation de h5ai
cd /var/seedbox
wget https://release.larsjung.de/h5ai/h5ai-0.27.0.zip
unzip h5ai-0.27.0.zip
rm h5ai-0.27.0.zip

sudo nano /etc/apache2/sites-available/seedbox.conf
```

----------------------------------------------

```
# configurez et adapté selon vos besoin
<VirtualHost *:80>
        ServerAdmin ...@purexo.eu #à remplacer
        ServerName seed.purexo.eu #à remplacer
        ServerAlias seed.purexo.eu #à remplacer
        DocumentRoot /var/seedbox
        <Directory /var/seedbox>
                Options +Indexes +FollowSymLinks +MultiViews
                AllowOverride All
                Require all granted
        </Directory>
        <Location />
		Order allow,deny
		Allow from all
	</Location>
        DirectoryIndex  index.html  index.php  /_h5ai/server/php/index.php
</VirtualHost>
```

Une fois vos modifications faites, enregistrez, puis :

```bash
sudo a2ensite seedbox
sudo service apache2 restart
sudo chown -R debian-transmission:debian-transmission /var/seedbox
sudo service transmission-daemon start
```

## Bonus pour rendre les fichiers privés
Apache + h5ai ça permet d'avoir une jolie interface d'index, de cocher des fichiers pour en dl plusieurs à la fois, etc...    
hésitez pas à aller éditer la configuration `/var/seedbox/_h5ai/conf`  
Mais tout le monde y a accès, donc un peu de configuration apache pour régler ça

```bash
cd /var/seedbox
htpasswd -c .htpasswd votreusername # pour le premier user
htpasswd .htpasswd autreusername # pour les suivants
nano .htaccess
```

----------------------------------------

```
AuthType Basic
AuthName "Seedbox area"
AuthUserFile /var/seedbox/.htpasswd
AuthGroupFile /dev/null
Require valid-user
```

## Votre interface administrateur
Au choix vous pouvez utiliser l'interface web ```http://votre.domaine:9091/```, ça fait le taf mais autant utiliser [transmission-remote](http://sourceforge.net/projects/transgui/) qui est plus complet dans son interaction avec votre serveur.

Voila, maintenant vous pourrez télécharger vos isos de distribution linux préférés ;-)

## Mes Sources / Librement inspiré de
- https://www.guillaume-leduc.fr/la-seedbox-facile-sous-debian-avec-transmission.html (quelques petits rappel)
- http://www.system-linux.eu/index.php?post/2009/04/23/Mettre-en-place-un-htaccess-avec-htpasswd (pour le htpasswd, je le savais mais ma mémoire est courte xD)
- mes connaissances/expériences personnelles