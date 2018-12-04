# Projet Uno!

Bienvenue sur le git de notre projet Uno. Le projet était de réaliser un jeux en combinant trois technologies. Java, NodeJs ainsi que React.


# Prérequis

Pour pouvoir utiliser le projet vous devez d'abord avoir **NodeJs** d'installé sur votre machine.
**Sails** doit être installé via la commande `npm install sails`.
Vous devez également disposer de **Java sdk**.

## Lancer le jeu
- Pour lancer le jeu vous devez au préalable lancer tous les services **Java**,  
- Une fois ceci fait, lancez l'application **sails**  et l'application React au travers des commandes suivantes dans les dossiers adéquats:
>sails lift.
>npm start
- Il vous faudra également lancer le service mongodb et vous y connecter grâce a une interface utilisateur ou un terminal.
- Un autre des prérequis dû à la version que l'on vous fournit est d'importer la base de données contenant toutes les cartes via le fichier CSV.
- Vous devrez également modifier les lignes de code suivantes : 
> Commenter la ligne 40 de et dé-commenter  la ligne 38 du fichier gameService dans l'engine

## Connexion au jeu
Pour vous connecter, vous devrez vous rendre sur l'url http://localhost:3000/
