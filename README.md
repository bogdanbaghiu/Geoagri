# Exercitiu 1 - Manage IPs

Se da un fișier text care conține pe fiecare linie câte o adresă IP. În baza acestui fișier text generează un fișier XLS
care să conțină pe fiecare linie următoarele informații, separate pe fiecare coloană:
IP (preluat din fișierul text)
Numele țării unde se află acest IP Regiunea din acea tară unde se află acest IP Orașul din regiunea unde se află acest
IP Instrucțiuni:
folosește API-ul de la adresa freegeoip.app pentru a obține informațiile; IP-urile din fișier se presupun a fi valide,
nefiind nevoie de validarea lor; Exemple de IP-uri: 8.8.8.8 , 140.82.121.3

# Exercitiu 2 - Manage IPs

Se da un șir de caractere care conține paranteze rotunde. Scrie o funcție care
verifică dacă orice paranteză deschisă are corespondent într-o paranteză închisă
(orice paranteză care se deschide se și închide). Exemple de șiruri de caractere:
    a) a(b(x)d)efghijkl
    b) (123).)(qw(e)

# Exercitiu 3 - Duplicate Transactions

Se dă o listă de tranzacții care reprezintă plățile făcute dintr-un cont bancar către
un alt cont. Câteodată se pot înregistra erori, iar unele tranzacții se vor duplica.
Trebuie să găsim aceste tranzacții pentru a le putea anula. Identificarea lor se face
găsind tranzacțiile care au aceleași informații mai puțin identificatorul de
tranzacție și data/ora la care au fost făcute. Astfel, o tranzacție se consideră
duplicatul alteia dacă cele două au același valori pe cheile sourceAccount ,
targetAccount , amount și sunt făcute la mai puțin de 1 minut una de cealaltă.
