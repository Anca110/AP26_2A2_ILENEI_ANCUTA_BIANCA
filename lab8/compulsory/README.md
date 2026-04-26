Proiectul contine urmatoarele clase:

Clasa MainFrame reprezinta fereastra principala a aplicatiei. 
In aceasta sunt adaugate cele 3 panouri: ConfigPanel, DrawingPanel si ControlPanel, folosind BorderLayout (sus, centru, jos).

Clasa ConfigPanel contine partea de sus a aplicatiei unde utilizatorul introduce numarul de randuri si coloane si apasa butonul Draw pentru a genera grila.

Clasa DrawingPanel este partea centrala unde se deseneaza labirintul. Am folosit o matrice Cell[][] in care fiecare celula retine daca are sau nu pereti (sus, dreapta, jos, stanga). 
Desenarea se face in metoda paintComponent folosind coordonate calculate pentru fiecare celula.

Clasa ControlPanel contine butoanele Create, Reset si Exit. Butonul Create elimina aleatoriu pereti din celule pentru a forma un labirint simplu, Reset pune toti peretii la loc, iar Exit inchide aplicatia.

Clasa Cell reprezinta o celula din grila si contine 4 variabile booleene pentru pereti (topWall, rightWall, bottomWall, leftWall).

Aplicatia permite crearea unei grile de dimensiuni diferite, generarea unui labirint simplu prin eliminarea aleatorie a peretilor si resetarea acestuia.

Pentru realizarea interfetei grafice am folosit biblioteca Swing.