Advanced

Proiectul contine urmatoarele clase:

Clasa **Intersection** reprezinta o intersectie din oras. Aceasta contine numele intersectiei si metoda **toString** pentru afisare.

Clasa **Street** reprezinta o strada care uneste doua intersectii. Aceasta contine numele strazii, lungimea ei si cele doua intersectii pe care le leaga. Clasa implementeaza interfata **Comparable**, pentru ca strazile sa poata fi sortate crescator dupa lungime prin metoda **compareTo**.

Clasa **City** descrie orasul. Aceasta contine lista de intersectii, lista de strazi si o mapa in care fiecare intersectie are o lista cu strazile acelei intersectii. In aceasta clasa exista si metode pentru adaugarea unei intersectii si a unei strazi (**addIntersection** si **addStreet**).

Clasa **Solution** contine lista de strazi alese si costul total al solutiei.

Clasa **Kruskal** implementeaza algoritmul folosit pentru determinarea unor solutii pentru conectarea intersectiilor cu cost cat mai mic. Mai intai strazile sunt sortate crescator dupa cost. Dupa aceea, algoritmul ia strazile pe rand si le adauga doar daca nu formeaza ciclu. Astfel, fiecare intersectie incepe intr-un grup separat, iar pe masura ce se adauga strazi, grupurile se unesc. Daca cele doua capete ale unei strazi se afla deja in acelasi grup, strada nu este adaugata, deoarece se formeaza un ciclu.

Metoda **findSolutions** returneaza mai multe solutii posibile. Pentru ca solutiile sa fie diferite, de fiecare data se sare cate o strada din lista sortata. La final toate solutiile sunt sortate dupa costul total.

Clasa **Algorithm** este folosita pentru determinarea rutei masinii de intretinere care trebuie sa parcurga intersectiile orasului. In metoda **findRoute** mai intai se construieste arborele minim folosind clasa **Kruskal**. Pe baza strazilor obtinute se construieste o mapa cu intersectiile vecine.
Apoi se parcurg intersectiile pornind de la prima intersectie si se construieste o ruta in ordinea in care sunt vizitate. La final se adauga din nou intersectia de start pentru ca masina sa se intoarca la punctul de plecare. Lungimea rutei este sumaa lungimilor strazilor dintre intersectiile parcurse.

Clasa **Route** reprezinta ruta obtinuta. Aceasta contine lista intersectiilor parcurse si lungimea totala a rutei.

Clasa **Generator** este folosita pentru generarea unui oras aleator. Metoda **generate** creeaza un numar de intersectii si pentru fiecare intersectie genereaza coordonate(x, y) aleatorii. Pe baza acestor coordonate se calculeaza lungimea strazilor dintre intersectii si se construieste orasul.

Clasa **Main** creeaza un exemplu de oras si implementeaza metodele.
Este apelata clasa **Algorithm**, care determina ruta masinii si afiseaza lungimea totala a acesteia. De asemenea prin clasa **Generator** este creat un oras aleator si se determina o ruta pentru acesta.