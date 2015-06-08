<p>Bonjour, je vous fournis un petit tuto pour installer et configurer une seedbox simplement sur votre serveur.</p>
<p>Mon système est basé sur transmission-daemon/transmission-remote et apache2 + h5ai (pour la beauté). Je pars du principe que votre apache est installé et configuré correctement, et que vous avez les bases dans la configuration d'apache, notamment sur les VirtualHost.</p>
<h2>Prérequis</h2>
<ul>
<li>Debian 8</li>
<li>Apache >= 2.4</li>
</ul>
<p>Sinon il y aura quelques configurations à adapter ;-)</p>
<h2>Installation</h2>
<pre><code class="bash">sudo apt-get install transmission-daemon
sudo service transmission-daemon stop
</code></pre>
<h2>Configuration</h2>
<h3>Transmission</h3>
<p>Ensuite vous pourrez éditer le fichier de configuration (assurez vous à chaque fois que vous éditerez le fichier, que transmission-daemon sois stoppé) : <code>/etc/transmission-daemon/settings.json</code>. Voici les paramètres à modifier :</p>
<pre><code class="JSON">{
    "download-dir": "/var/seedbox",
    "incomplete-dir": "/var/seedbox/.incomplete", 
    "incomplete-dir-enabled": true, 
    "rpc-username": "VotreUserName", 
    "rpc-password": "VotreMDPEnCLair", // quand le service démarrera il chiffrera votre mot de passe
    "rpc-whitelist-enabled": false, 
    "umask": 22, // pour que apache soit pas emmerdé plus tard
}
</code></pre>
<h3>Apache</h3>
<pre><code class="bash">sudo mkdir /var/seedbox /var/seedbox/.incomplete

# instalation de h5ai
cd /var/seedbox
wget https://release.larsjung.de/h5ai/h5ai-0.27.0.zip
unzip h5ai-0.27.0.zip
rm h5ai-0.27.0.zip

sudo nano /etc/apache2/sites-available/seedbox.conf
</code></pre>
<pre><code>```
# configurez et adapté selon vos besoin
&lt;VirtualHost *:80&gt;
        ServerAdmin ...@purexo.eu #à remplacer
        ServerName seed.purexo.eu #à remplacer
        ServerAlias seed.purexo.eu #à remplacer
        DocumentRoot /var/seedbox
        &lt;Directory /var/seedbox&gt;
                Options +Indexes +FollowSymLinks +MultiViews
                AllowOverride All
                Require all granted
        &lt;/Directory&gt;
        &lt;Location /&gt;
        Order allow,deny
        Allow from all
    &lt;/Location&gt;
        DirectoryIndex  index.html  index.php  /_h5ai/server/php/index.php
&lt;/VirtualHost&gt;
</code></pre>
<p>Une fois vos modifications faites, enregistrez, puis :</p>
<pre><code class="bash">sudo a2ensite seedbox
sudo service apache2 restart
sudo chown -R debian-transmission:debian-transmission /var/seedbox
sudo service transmission-daemon start
</code></pre>
<h2>Bonus pour rendre les fichiers privés</h2>
<p>Apache + h5ai ça permet d'avoir une jolie interface d'index, de cocher des fichiers pour en dl plusieurs à la fois, etc&hellip;
<br  />hésitez pas à aller éditer la configuration <code>/var/seedbox/_h5ai/conf</code>
<br  />Mais tout le monde y a accès, donc un peu de configuration apache pour régler ça</p>
<pre><code class="bash">cd /var/seedbox
htpasswd -c .htpasswd votreusername # pour le premier user
htpasswd .htpasswd autreusername # pour les suivants
nano .htaccess
</code></pre>
<pre><code>```
AuthType Basic
AuthName "Seedbox area"
AuthUserFile /var/seedbox/.htpasswd
AuthGroupFile /dev/null
Require valid-user
</code></pre>
<h2>Votre interface administrateur</h2>
<p>Au choix vous pouvez utiliser l'interface web <code>`http://votre.domaine:9091/</code>`, ça fait le taf mais autant utiliser <a href="http://sourceforge.net/projects/transgui/">transmission-remote</a> qui est plus complet dans son interaction avec votre serveur.</p>
<p>Voila, maintenant vous pourrez télécharger vos isos de distribution linux préférés ;-)</p>
<h2>Mes Sources / Librement inspiré de</h2>
<ul>
<li><a href="https://www.guillaume-leduc.fr/la-seedbox-facile-sous-debian-avec-transmission.html">https://www.guillaume-leduc.fr/la-seedbox-facile-sous-debian-avec-transmission.html</a> (quelques petits rappel)</li>
<li><a href="http://www.system-linux.eu/index.php?post/2009/04/23/Mettre-en-place-un-htaccess-avec-htpasswd">http://www.system-linux.eu/index.php?post/2009/04/23/Mettre-en-place-un-htaccess-avec-htpasswd</a> (pour le htpasswd, je le savais mais ma mémoire est courte xD)</li>
<li>mes connaissances/expériences personnelles</li>
</ul>
