###README
#Mustapha NEZZARI
##Contenu de l’archive
- Un dossier src avec toutes les sources
- Un fichier exécutable .jar
- Un script de lancement sur un système unix
##Pré-requis
- Linux ou Mac
- Filezilla client !
- JRE 1.8 (Utilisation de lambda)
##Etapes à suivre
- Dans une ligne de commande, se placer dans le dossier du projet
- Exécutez la commande “chmod +x run.sh && ./run.sh”
- Un dossier users est créé dans le même répertoire
- Connectez vous au serveur avec les identifiants :
- login : test
- mot de passe : test
- Testez les commandes:
- CDUP
- CWD
- DELE
- EPRT
- EPSV
- LIST
- MKD
- NOOP
- PASS
- PASV
- PORT
- PWD
- QUIT
- RETR
- RMD
- STOR
- USER
Voici un scénario de test :
- Lancez Filezilla et le serveur
- Connectez vous avez les identifiants test et test
- Vous atterrissez dans le dossier test
- Créez un dossier “hello”
- Allez dans ce dossier
- Créez un fichier dans ce dossier
- Envoyez un fichier quelconque
- Supprimer le fichier
- Revenez en arrière avec “..”
- Supprimez le dossier
##Difficultés rencontrées
Je suis sur mac.
Le serveur FTP ne fonctionne plus sur windows depuis que j’ai utilisé des classe spécifique aux
systèmes unix (PermissionFile, etc)
De plus, il ne fonctionne plus (pour je ne sais quelle raison) avec une ligne de commande sous
mac (et peut être linux) car il a quelque problème avec le \n (J’ai pourtant bien utilisé
System.lineSeparator()). C’est pourquoi je recommande l’utilisation de filezilla pour pouvoir tester
complètement le serveur..
