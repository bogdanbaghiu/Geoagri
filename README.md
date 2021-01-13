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

# Exercitiu 4 - Identificarea erorii

Descrie în câteva rânduri cum ai încerca rezolvarea problemei descrisă mai jos. Nu
există un răspuns corect, nu trebuie să scrii cod, trebuie doar să scrii care ar putea
fi problema precum și pașii pe care i-ai urma pentru a o identifica.

## Problemă
Se presupune că ai un sistem format dintr-o aplicație backend (o aplicație care
stă pe un server și răspunde la cereri de la clienții din exterior) o bază de date
(cu care aplicația backend comunică pentru a-și stoca informațiile) și o aplicație
client (aplicație care comunică cu aplicația backend pentru a citi și stoca
informații în baza de date). Un utilizator al sistemului apasă un buton într-o
interfață a aplicației client și, după o întârziere de 10 secunde în care
aplicația a înghețat devenind neaccesibilă, aceasta întoarce rezultatul dorit.

## Răspuns
Problema care o identific este înghețarea aplicației. O aplicație când solicită niște date trebuie să poată fi accesibilă. 
Pentru a soluționa această problemă aș propune să se folosească metode async/await. Aceste metode permit aplicației rularea în timp dinamic și se evită înghețarea ei.
De exemplu, când se intră pe o pagină care are multe imagini și text (de exemplu un ziar), e mai de dorit în general ca să se încarce cu esursele care vin una câte una, precum imaginele care sosec în diferite etape de timp și user-ul să poată citi articolul, decât să stea locat și apoi să ruleze site-ul.
