# Escape Game

Application JAVA en mode console construite avec Maven. Cette application est un jeu basé sur le principe de recherche d'une combinaison à X chiffres.
Il y a 3 modes de jeu :
- le mode Challengeur : l'intelligence artificielle définit la combinaison, le joueur doit la deviner en X essais.
- le mode Défenseur : le joueur définit la combinsaon, l'intelligence artificielle doit la deviner en X essais.
- le mode Duel : l'intelligence artificielle et le joueur définisent chacun une combinaison que l'autre doit deviner en X essais. 

Le nombre d'essais, le nombre de chiffres dans la combinaison sont paramétrables dans un fichier de configuration.
Un mode développeur, qui permet d'afficher la combinaison au lancemet du mode de jeu, est également paramétrable via la fichier de configuration.

## Prérequis

**Un JDK version 8** minimum est requis pour faire fonctionner l'application. 2 possibilités :
- le télécharger depuis le site d'oracle (le JDK est utilisable gratuitement pour des projets non commerciaux) :
	- Pour télécharger le JDK : https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
	- Les instructions d'installation se trouve ici : https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

- le télécharger depuis AdoptOpenJDK (utilisable gratuitement quelque soit les projets)
	- pour télécharger le JDK : https://adoptopenjdk.net/
	- les instructions d'installation se trouve ici : https://adoptopenjdk.net/installation.html#

**Apache Maven 3** est requis pour compiler facilement les sources.
- Pour télécharger Apache Maven : https://maven.apache.org/download.cgi
- Pour installer Apache Maven : https://maven.apache.org/install.html

## Récupérer le projet 

### via git clone
En ligne de commande, se placer dans le répertoire voulu puis exécuter la commande `git clone https://github.com/gaelle8278/oc-poec-dev-java-escape-game.git`. Une fois le projet récupéré renommer le dossier escape-game pour plus de simplicité.

- Sous Windows, dans l'invite de commande :
```
cd D:/DevProjets
git clone https://github.com/gaelle8278/oc-poec-dev-java-escape-game.git
rename oc-poec-dev-java-escape-game escape-game
```

- Sous Linux, dans un terminal :
```
cd ~/DevProjets
git clone https://github.com/gaelle8278/oc-poec-dev-java-escape-game.git
mv oc-poec-dev-java-escape-game escape-game
```

###via l'archive zip

Télécharger l'archive depuis la page du projet : https://github.com/gaelle8278/oc-poec-dev-java-escape-game. Cliquer sur le bouton vert "Clone or download" puis Download ZIP.

Se rendre dans le dossier où l'archive a été téléchargée puis la décompresser, l'archive se nomme oc-poec-dev-java-escape-game-master.zip : 
- Sous Windows utiliser son utilitaire préféré : winrar, 7zip, peazip ...
- Sous Linux utiliser la commande : `unzip oc-poec-dev-java-escape-game-master.zip`

Cela crée le dossier oc-poec-dev-java-escape-game-master, le renommer escape-game (soit en faisant clic droit sur le dossier puis renommer soit en ligne de commande).

## Le fichier de configuration
Le dossier escape-game contient plusieurs dossiers et fichiers notamment un fichier de configuration qui se nomme game.properties. Son formalisme est : une ligne pour chaque paramètre avec la forme paramètre = valeur.
Il contient 3 paramètres :
* modeDeveloper pour définir si le mode développeur est actif ou non. Valeurs possibles : 0 pour mode inactif, 1 pour mode actif.
* numberTests pour indiquer le nombre d'essais possibles. La valeur doit être un entier entre 1 et 10.
* combinationLength pour indiquer la longueur de la combinaison. La valeur doit être un entier entre 1 et 20.

## Compiler l'application avec Apache Maven

Dans le répertoire source de l'application lancer la commande `mvn compile dependency:copy-dependencies`

```
cd escape-game
mvn compile dependency:copy-dependencies
```

## Lancer l'application

Se placer dans le sous-dossier target/classes du répertoire de l'application (escape-game) puis exécuter la commande java pour lancer l'application :

Sous Windows :
```
cd target/classes
java -classpath .;lib\* dev.gaellerauffet.escapegame.Main
```
Sous Linux :
```
cd target/classes
java -classpath .:lib/* dev.gaellerauffet.escapegame.Main
```


