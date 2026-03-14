# Homework

Proiectul contine urmatoarele clase:

Clasa **Intersection** reprezinta o intersectie din oras. Aceasta contine numele intersectiei si metoda `toString` pentru afisare.

Clasa **Street** reprezinta o strada care uneste doua intersectii. Aceasta contine numele strazii, lungimea ei si cele doua intersectii pe care le leaga. Clasa implementeaza interfata `Comparable`, pentru ca strazile sa poata fi sortate crescator dupa lungime.

Clasa **City** descrie orasul. Aceasta contine lista de intersectii, lista de strazi si o mapa in care  fiecare intersectie are o lista cu strazile acelei intersectii. In aceasta clasa exista si metode pentru adaugarea unei intersectii si a unei strazi.

Clasa **Solution** contine lista de strazi alese si costul total al solutiei.

Clasa **Kruskal** implementeaza algoritmul folosit pentru determinarea unor solutii pentru conectarea intersectiilor cu cost cat mai mic. Mai intai strazile sunt sortate crescator dupa cost. Dupa aceea, algoritmul ia strazile pe rand si le adauga doar daca nu formeaza ciclu.
Astfel, fiecare intersectie incepe intr-un grup separat, iar pe masura ce se adauga strazi, grupurile se unesc. Daca cele doua capete ale unei strazi se afla deja in acelasi grup, strada nu este adaugata, deoarece se formeaza un ciclu.

Metoda `findSolutions` returneaza mai multe solutii posibile. Pentru ca solutiile sa fie diferite, de fiecare data se sare cate o strada din lista sortata. La final toate solutiile sunt sortate dupa costul total.

Clasa **Main** creeaza un exemplu de oras si testeaza metodele.
Pentru generarea numelor am folosit biblioteca externa **javafaker** (in `pom.xml`) cu care am generat nume aleatorii pentru intersectii si strazi.
Interesectiile sunt create cu ajutorul `IntStream`, iar strazile sunt stocate intr-o lista `LinkedList`.
Astfel, am creat o mapa pentru un oras.
De asemenea, folosind API-ul Java Stream afisez strazile cu lungime mai mare decat o valoare data si pentru care cel putin una dintre intersectii este conectata la minimum 3 strazi.
