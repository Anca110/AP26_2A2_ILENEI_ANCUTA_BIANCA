



Proiectul contine urmatoarele clase:

Profile este o interfata comuna pentru clasele Person si Company.

Clasa Person contine: id, nume, data de nastere si o lista de relatii cu alte profile care sunt stocate intr-un Map format din cheie-profilul cu care exista relatie si o valoare-tipul de relatie.

Clasa Company contine: id, nume, locatie. Companiile pot avea relatii cu o persoana.

Clasele Programmer si Designer sunt specializari ale clasei Person.





Clasa SocialNetwork gestioneaza reteaua sociala.
Aceasta contine o lista de profile. Reteaua este vazuta ca un graf in care nodurile sunt profile, iar muchiile reprezinta relatiile dintre acestea.
In aceasta clasa exista urmatoarele metode:

-addProfile pentru adaugarea profilelor in retea.

-Importance care returneaza numarul de relatii in care se afla un profil dupa care ulterior se sorteaza.

-getNeighbors care determina vecinii unui profil dat (profilele cu care acesta se afla in relatie)

-findArticulationPoints determina punctele de articulatie din graf(nodurile care daca sunt eliminate pot separa graful in mai multe componente conexe).
Pentru determinarea acestor puncte am folosit o parcurgere DFS in timpul careia pentru fiecare nod am retinut timpul de descoperire al acestuia (discoverTime) si cea mai mica valoarea de descoperire accesibila din subarborele nodului curent (minTimeReach).
Pe baza acestor valori se determina daca un nod este punct de articulatie.
Daca un nod are un copil in arborele dfs care nu are legatura catre un stramos al nodului, atunci acel nod este punct de articulatie

-findBiconectedComponents determina o multime de noduri din graf in care nu exista puncte de articulatie. 
Mai intai am determinat punctele de articulatie.
Parcurg toate profilele din graf, iar pentru cele care nu au fost inca vizitate si nu sunt puncte de articulatie incep o parcurgere DFS pentru a construi cate o componenta.
Dupa ce componenta a fost creata se verifica vecinii fiecarui nod din componenta, iar daca unul este punct de articulatie si nu se afla deja in componenta se adauga.
Toate aceste componente sunt adaugate intr-o lista


Clasa SocialNetworkTest contine teste realizate cu JUnit pentru verificarea unor metode.

Clasa Main creaza un exemplu de retea sociala si testeaza metodele.