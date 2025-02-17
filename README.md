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
mvn clean verify
ouvrir le site en raccourci

### Actions à réaliser

- Ajout de la Promotion dedans
- Jaune se retire quand la pièce s'est déplacé
- Changement de pièce quand c'est de la même couleur que la pièce choisi
- Ajoute la Fin de partie quand la méthode finPartie()
[TAG2]

### Autres TAGs
- [TAG3] Implémenter les différentes fonctions du controllers avec J2EE, JSP, JS (fonctionnement d'un jeu d'échec en version web)
- [TAG4] Implémenter les différentes fonctions du controllers (fonctionnement d'un jeu d'échec en version mobile)
- [TAG5] Ajout Menu, Fonction de Sauvegarde, Système de création de Terrain, Triple Répétition, Système de Cadence, Echecs et le Mat