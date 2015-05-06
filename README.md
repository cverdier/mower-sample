# À propos de l'application exemple MowItNow - Mower Driver

## Conception
Cette application est un exemple de conception objet Java simple. Le but est de piloter automatiquement des tondeuses sur une surface rectangulaire, représentée en cases.
Elle est conçue comme une librairie autour des classes et interfaces principales suivantes :
* Mower : Objet de base pour une tondeuse pilotable
* Driver : Pilotage automatique de tondeuses
* Action : Action atomique d'une tondeuse dans le système
* ConfigurationService : Configuration du système de pilotage
* RenderService : Affichage d'informations du système de pilotage
Des implémentations de base sont fournies pour un usage d'exemple.
Une classe MowerCLI permet un fonctionnement exécutable en ligne de commande.
L'application est destinée à un JRE 1.7.


## Construction
La construction requiert un JRE 1.7.
L'application utilise Maven pour sa construction et la gestion de dépendances :
* JUnit

Construction (incluant les tests)
<pre>mvn clean package</pre>

Tests uniquement
<pre>mvn test</pre>


## Tests
Des tests unitaires sont inclus pour les éléments centraux de l'applications :
* Les implémentations d'Action
* L'implémentation de Driver

Des tests plus complets (scénarios) sont inclus, permettant de simuler une utilisation simple de l'application complète


## Utilisation
L'application nécessite un JRE 1.7
Le fonctionnement fourni permet uniquement la lecture de commandes depuis un fichier (le paramètre pour le fichier est requis dans la commande suivante).
<pre>java -jar mower-driver-1.0.jar -f path/to/file</pre>

Les erreurs sont tracées dans la console.
