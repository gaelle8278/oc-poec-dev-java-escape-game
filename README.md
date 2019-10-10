# Escape Game

Application JAVA en mode console construite avec Maven. Cette application est un jeu basé sur le principe de recherche d'une combinaison à X chiffres.
Il y a 3 modes de jeu :
- le mode Challengeur : l'intelligence artificielle définit la combinaison, le joueur doit la deviner en X essais.
- le mode Défenseur : le joueur définit la combinsaon, l'intelligence artificielle doit la deviner en X essais.
- le mode Duel : l'intelligence artificielle et le joueur définisent chacun une combinaison que l'autre doit deviner en X essais. 

Le nombre d'essais, le nombre de chiffres dans la combinaison sont paramétrables dans un fichier de configuration.
Un mode développeur, qui permet d'afficher la combinaison au lancemet du mode de jeu, est également paramétrable via la fichier de configuration.

## Récupérer le projet 
`git clone https://github.com/gaelle8278/oc-poec-dev-java-escape-game.git`

ou télécharger le dossier zip depuis la page du projet : https://github.com/gaelle8278/oc-poec-dev-java-escape-game.

## Le fichier de configuration
Le fichier de configuration se nomme game.properties. Son formalisme est : une ligne pour chaque paramètre avec la forme paramètre = valeur.
Il contient 3 paramètres :
* modeDeveloper pour définir si le mode développeur est actif ou non. Valeurs possibles : 0 pour mode inactif, 1 pour mode actif.
* numberTests pour indiquer le nombre d'essais possibles. La valeur doit être un entier entre 1 et 10.
* combinationLength pour indiquer la longueur de la combinaison. La valeur doit être un entier entre 1 et 20.

## Lancer l'application avec Maven

Dans le répertoire source de l'application :
`mvn exec:java -Dexec.mainClass=dev.gaellerauffet.escapegame.Main`

## Compiler l'application avec Maven

Dans le répertoire source de l'application :
`mvn compile assembly:single`
ou 
`mvn package`

Exécution de l'application (du jar) :
java -jar target/escapegame-0.0.1-SNAPSHOT-jar-with-dependencies.jar