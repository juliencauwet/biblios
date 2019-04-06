Le projet est composé de 4 applications et d'une base de données:
* un web service connecté à la base de données, qui expose son API par un WSDL.
* une application client, pour les utilisateurs permettant d'envoyer des requêtes aux web service pour la gestion des livres (consultation, emprunt, réservtion, prolongation ...).
* un batch qui envoie des emails de notifications aux personnes n'ayant pas retourné l'emprunt avant la date limite.
* un batch qui envoie des emails aux emprunteurs dont la date limite de fin d'emprunt est proche.


#### 1. Build le webservice afin de créér l'image Docker:
Depuis le dossier source (biblios), saisir en ligne de commande:
  * _cd biblioback_
  * _mvn clean install_

#### 2. Déployer 
Déployer le web service biblioback et la base de données dans un container
  * _cd docker_
  * _docker-compose up_
  
  <b>L'application est alors déployée dans un container accessible depuis le port 1111 de la machine hôte</b>

#### 3. Build l'application client et créer l'image docker:
se rendre dans le dossier biblioweb 
  * _cd ../../biblioweb_
  * _mvn clean install_

#### 4. Déployer 
Déployer biblioweb dans un container
  * _cd docker_
  * _docker-compose up_

  <b>L'application est alors déployée dans un container accessible depuis le port 1112 de la machine hôte</b>

#### 5. Build le premier batch et créer l'image docker:
se rendre dans le dossier bibliobatch 
  * _cd ../../bibliobatch
  * _mvn clean install_  
  
#### 6. Déployer 
Déployer le batch dans un container
  * _cd docker_
  * _docker-compose up_

  <b>Le batch est alors déployé 
  
#### 7. Build le second batch et créer l'image docker:
se rendre dans le dossier expiring-soon-batch
  * _cd ../../expiring-soon-batch
  * _mvn clean install_  
  
#### 8. Déployer 
Déployer le batch dans un container
  * _cd docker_
  * _docker-compose up_

  <b>Le batch est alors déployé 
