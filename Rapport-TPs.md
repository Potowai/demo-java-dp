# Rapport Design Patterns - GRASPS & GoF

## TP GRASPS — Refactoring de ReservationController

### Objectif
Refactorer `ReservationController` en appliquant les principes GRASPS.

### Principes appliqués

| Principe | Application |
|---|---|
| **Low Coupling** | Interfaces `IClientDao`, `ITypeReservationDao`, `IReservationService` ; injection des dependances par constructeur |
| **High Cohesion** | Chaque classe a une responsabilite unique : controller, service, dao, bean |
| **Information Expert** | `Reservation.calculerTotal()` utilise ses propres donnees (`nbPlaces`, `client`) |
| **Creator** | `Client.creerReservation()` cree et initialise la reservation |
| **Controller** | `ReservationController` = point d'entree, delegue au service |
| **Indirection** | Controller -> Service -> DAOs |
| **Pure Fabrication** | `DateUtils` pour la conversion de date |
| **Law of Demeter** | `client.addReservation()` au lieu de `client.getReservations().add()` |

### Architecture finale

```
services/                  daos/
  IReservationService        IClientDao
  impl/                      ITypeReservationDao
    ReservationServiceImpl   impl/
                               ClientDaoImpl
                               TypeReservationDaoImpl

ReservationController -> IReservationService -> IClientDao / ITypeReservationDao
                                                -> Client.creerReservation()
                                                -> Reservation.calculerTotal()
```

### Tests
- 9 tests : cas passants (4 combinatoires premium x type, 0 place, lie au client) + cas non passants (client null, type null, date invalide)
- Stubs lambda pour les DAOs (`IClientDao clientDao = id -> CLIENT_PREMIUM`)

---

## TP BUILDER — `fr.sdv.builder`

### Objectif
Creer un Builder fluent pour construire des instances de `Produit`.

### Classes
- `Categorie` (enum) : ALIMENTAIRE, ELECTROMENAGER, LOISIR, VETEMENT
- `Produit` : nom, prix, categorie (constructeur package-private)
- `ProduitBuilder` : fluent `setNom().setPrix().setCategorie().build()` avec validation

### Tests (8)
- **Cas passants** : nominal, fluent, prix=0
- **Cas non passants** : prix negatif, nom null, nom vide, categorie null, rien configure

---

## TP FACTORY METHOD — `fr.sdv.factory`

### Objectif
Creer une Factory qui retourne un element en fonction d'un type (enum).

### Classes
- `TypeElement` (enum) : INGREDIENT, ALLERGENE, ADDITIF
- `Element` (abstract) : nom, valeur, unite
- `Ingredient`, `Allergene`, `Additif` extends Element
- `ElementFactory.create(TypeElement, String, double, String)` : factory method

### Tests (5)
- **Cas passants** : ingredient, allergene, additif, valeur=0
- **Cas non passants** : type null

---

## TP STRATEGY — `fr.sdv.strategy`

### Objectif
Refactorer la classe `Tri` (if/else avec types en int) en utilisant le Strategy pattern.

### Classes
- `Strategy` (interface) : `trier(Integer[] arr)`
- `BulleSort`, `InsertionSort`, `SelectionSort` : implementations
- `TypeTri` (enum) : BULLE, INSERTION, SELECTION
- `StrategyFactory` : retourne la strategie selon le type
- `Tri` : `setStrategy(Strategy)` + `exec(Integer[])`

### Tests (4)
- **Cas passants** : bubble sort, insertion sort, selection sort (verification du tri)
- **Cas non passants** : aucune strategie definie

---

## TP STATE — `fr.sdv.state`

### Objectif
Gerer les etats d'une commande (Creation, Paiement, EnLivraison, Annulee) avec le State pattern.

### Classes
- `EtatCommande` (interface) : ajouterProduit, payer, livrer, annuler
- `CreationEtat`, `PaiementEtat`, `EnLivraisonEtat`, `AnnuleeEtat` : comportements specifiques
- `Commande` : contexte qui delegue a l'etat courant
- `Produit` : nom, prix

### Regles metier
| Action \ Etat | CREATION | PAIEMENT | EN_LIVRAISON | ANNULEE |
|---|---|---|---|---|
| ajouterProduit | OK | - | - | - |
| payer | -> PAIEMENT | - | - | - |
| livrer | - | -> EN_LIVRAISON | - | - |
| annuler | -> ANNULEE | -> ANNULEE | Impossible | - |

### Tests (7)
- **Cas passants** : creation + paiement + livraison, annuler depuis creation, annuler depuis paiement
- **Cas non passants** : livrer sans paiement, ajout apres paiement, annuler en livraison, payer deux fois

---

## TP COMPOSITE — `fr.sdv.composite`

### Objectif
Representer une hierarchie d'entreprise (Employes et Services) avec le Composite pattern.

### Classes
- `IElement` (interface) : `calculerSalaire()`
- `Employe` (leaf) : retourne son salaire individuel
- `Service` (composite) : somme des salaires de tous ses elements (employes + sous-services)

### Tests (3)
- Hierarchie complete (6 employes, 3 services) : salaires Support=5500, Dev=6000, Direction=17800
- Employe seul
- Service vide

---

## Statistiques

| Metrique | Valeur |
|---|---|
| Packages crees | 7 : builder, factory, strategy, state, composite, daos/impl, services/impl |
| Fichiers Java | ~40 sources + tests |
| Tests unitaires | 36 (tous passent) |
| Patterns implementes | GRASPS (9 principes), Builder, Factory Method, Strategy, State, Composite |
