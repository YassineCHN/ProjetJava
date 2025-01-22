# Liste d'hypothèses

## Utilisateur et Personne
- Un utilisateur peut être une personne mais ne l'est pas forcément (ex : admin).
- Un login est unique.
- Une personne n'est pas forcément un utilisateur (on peut rattacher une personne existante à un utilisateur lors de la création de ce dernier).
- On ne peut pas modifier le rôle d'un utilisateur.
- Pour changer de rôle, il faut recréer un utilisateur et l'attacher à la personne.
- Chaque utilisateur a accès à son espace personnel pour modifier ses informations personnelles.
- Lors de l'ajout d'un utilisateur :
  - On affiche uniquement les personnes qui n'ont pas d'utilisateurs lié.
  - Si on sélectionne une personne qui ne correspond pas au rôle sélectionné, cela ne fonctionnera pas.
  - Si on sélectionne via la console développeur une valeur d'une personne qui a déjà un utilisateur, cela ne fonctionnera pas non plus.
- Lors de la suppression d'un utilsiateur :
  - Pour supprimer un utilisateur on détache d'abord la personne qui lui est rattaché

## Médecin
- Le médecin peut annuler un dossier
- Le médecin peut seulement visualiser les dossiers liés à son service 


## Membre du personnel
- Le personnel peut annuler un dossier
- Le personnel peut seulement visualiser les dossiers liés à son service 


## Patient
- Un patient peut avoir plusieurs dossiers d'hospitalisation.
- Le patient peut visualiser tout ces dossiers
- Modification de son dossier : le patient peut seulement l'annuler.

## Dossier d'Hospitalisation
- Le personnel peut seulement modifier la date d'arrivé et de départ
- Le médecin peut seulement modifier la date d'hospitalisation et annuler un dosier
- Les ID de service et de patient d'un dossier sont immuables.
- On ne permet pas de supprimer un dossier car pour le supprimer il faudrait qu'il ne soit rattaché à aucun service et aucun patient, cependant ce cas est impossible car on autorise pas la création d'un dossier pas rattaché 
- Un médecin peut créer un dossier d'hospitalisation et le rattacher à un autre service, mais une fois créé, il ne pourra plus le visualiser dans la liste des dossiers.

## Service
- Un médecin est rattaché à un service.
- Il est impossible de supprimer un service, tant qu'un médecin ou un personnel ou un dossier y est rattaché

## Journaux d'actes
- Dans nos htpothèses, ce qui est facturé est un "journal" d'actes.
  - le journal contient des lignes d'actes. Chaque ligne renseigne l'acte qui est associé au journal, sa quantité, un commentaire et le médecin qui a réalisé l'acte ainsi que la date de réalisation de l'acte.
  - il n'existe qu'un seul journal par dossier.
  - Par conséquent, il n'y qu'une seule facture par journal et donc par dossier.
- L'utilisation d'un journal se passe en 3 étapes :
  - à partir du dossier d'hospitalisation, accéder au journal

## Concernant la navigation entre les JSP
- On privilégie l'utilisation, s'ils sont présents, des boutons "Retour" qui permettent de retourner au menu principal
  - A noter que le mot "Home" dans la barre supérieur permet de retourner au menu principal


