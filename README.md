# Wided

# HealthyFood

## Présentation
Application Android réalisé en Kotlin.
Projet ESIEA 3A S2.
Matière : programmation mobile
J'ai réalisé cette application portant sur les repas sain et équilibré. Vous retrouverez l'ensemble des repas avec leurs images et des détails les concernant.

## Prérequis
- Installer Android Studio et ouvrir ce projet.
- Création de l'émulateur.

## Consignes respectées :
- Appel Api Rest ( je n'ai pas réussi a trouver une Api Rest food public avec des images mais le code fonctionne  )
- Cache Okhttp
- Ajout des ressources
- Écran : Home , My favorite et Add 
- Une RecyclerView horizontal et vertical
- Une activitée pour afficher en détail un élément
- Utilisation du Singleton Pattern
- Utilisation de Glide pour récuperer l'image à partir de son lien
- Utilisation du site Github 
- Application fonctionne toujours en mode avion grâce au Cache 
![](images/Cache.png)


## Fonctionnalités :


### Dans toute l'application
- Base de données :utilisation du Farebase-> Stocker et synchroniser les données en dehors de l'appliation 
- Création d'un BUCKET storage pour stocker toutes les images 
- Changement du titre sur chaque page 
- Changement de couleur pour les items séléctionner sur la barre de navigation
- Changement de l'icone par défaut de l'application

![](images/Icone.png)


### Écran Home
- Deux liste Dynamique : une au format verticale pour afficher les articles liker et non liker et une au format horizental pour afficher 
les articles non liker ou jamais rencontrer c'est à dire à decouvrir . 
- Une barre de navigation permet de naviguer entre les différents fragments ( HOME,FAVORITE ET ADD)
- Icon Star permet de liker ou disliker l'artitcle
- Posibilité de scroll horizentalement ou verticalement

![](images/Home.png)

### Écran des détails
- une fenetre surgissante ( Un Popup).
- Icon close pour fermer la page et revenir sur le home
- Une activitée avec les détails de chaque article
- Deux boutons: Un pour pouvoir supprimer ( icone trash ) l'article et un autre ( icone star)  pour pouvoir la mettre en favoris

![](images/Popup.png)



### Écran Favorite
- Une présentation des articles par leurs images .
- Recycler view en horizontal pour une meilleure présentation .
- Posibilité de scroll .
- Présence de l'icone Star pour pouvoir disliker .
- Posibilité d'ouvrir la déscription.

![](images/Favorite.png)

### Écran Add
- Ajouter un article "food"
- Charger une image à partir des images du téléphone
- inserer les nouvelles informations et envoyer à l'aide du bouton submit

![](images/Add.png)






