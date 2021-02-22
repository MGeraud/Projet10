# OCR---Projet-10

Projet 10 du parcours développeur d'application JAVA d'OpenClassRooms.

Dans ce projet, il s'agit de mettre à jour le projet 7 avec de nouvelles fonctionnalités envoyés par ticket via les _issues_ de GitHub.

## Mise à jour pour la production : 

Mettre à jour le schema de la base de données PostGreSQL via le script du dossier __Scripts sql__ > __updateDatabase__ > fichier : __v1Tov2.sql__

En fonction de l'hébergement qui avait été choisi , vous pouvez mettre à jour les différents applications (ocr_webapp , ocr_loan_api , ocr_bibliotheque et expired-loan-batch) en générant le nouveau jar dans chacun des fichiers via les commandes maven.


## Tests des api Rest (ocr_loan_api et ocr_bibliotheque)

Créer les base de données de test PostGreSQL via les scripts des dossiers __Script sql__ > __api bibliotheque__ et __api loan__ .

Lancer les test unitaires via :
> mvn test

Pour les test d'intégration, le choix a été fait de créer une collection sur Postman, avant de lancer cette collection, il vous faut lancer les API **avec le profil de test** via la commande : 
> mvn spring-boot:run -Dspring-boot.run.profiles=test
> 

Ensuite vous pouvez lancer la collection de test postman ici : [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/5e623ed6db9be25402cd)

 
