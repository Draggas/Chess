# CHESS

## Introduction

Projet personnels

## Description

Ce projet est un jeu d'échec qu'on peut jouer sur plusieurs plateformes :
- Sur un terminal, sans graphique
- Sur PC, avec un écran graphique (~)
* Sur PC ou Mobile, depuis un lien internet (x)
* Sur Mobile (x)

## Accès à l'application

### Version avec graphique :

**Linux :** 
Lancer le Run.sh

**Windows :** 
Lancer depuis un terminal à la racine du projet :
`mvn clean package`
`mvn javafx:run`

### Version sans graphique :

**Linux ou Windows :** Lancer depuis une application comme VSC ou Intellij

## Technos

- JAVA
- JAVAFX
- MAVEN
- Architecture MVC + Observers/Observable

---

**__Auteur :__** Lucas De Jesus Teixeira

**Tout droits réservés**

## Autres

### Lancer Couverture de Test avec Jacoco
mvn clean test / mvn clean verify
mvn verify
ouvrir le site en raccourci

### AVANT
- Remise à propre des différents classes (ergonomies, noms en anglais...)
- Implémentation du Controllers dans le ChessBoard

### CHESS AVEC JAVAFX [TAG2]
- Implémenter les différentes fonctions du controllers avec JavaFX (fonctionnement d'un jeu d'échec en version graphique)

### CHESS AVEC J2EE et JS [TAG3]
- Implémenter les différentes fonctions du controllers avec J2EE, JSP, JS (fonctionnement d'un jeu d'échec en version web)

### CHESS AVEC JAVA et Android Studio [TAG4]
- Implémenter les différentes fonctions du controllers (fonctionnement d'un jeu d'échec en version mobile)

### FIN DU PROJET : BONUS [TAG5]
- Ajout d'un Menu
- Ajout la fonction de Sauvegarde
- Système de création de Terrain
- Ajout Triple Répétition
- Système de Cadence
- Ajout des Echecs
- Ajout de l'Echec et Mat = Fin Du Jeu