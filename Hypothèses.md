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

## Patient
- Un patient peut avoir plusieurs dossiers d'hospitalisation.
- Modification de son dossier : le patient peut seulement l'annuler.

## Dossier d'Hospitalisation
- Les ID de service et de patient d'un dossier sont immuables.
- Pour supprimer un dossier, on initialise d'abord ces deux ID à `null`.
- Un médecin peut créer un dossier d'hospitalisation et le rattacher à un autre service, mais une fois créé, il ne pourra plus le visualiser dans la liste des dossiers.

## Service
- Un médecin est rattaché à un service.



