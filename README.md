Na koji način ovakva jedna biblioteka utvrđuje da li zadani string odgovara specifikaciji regularnog izraza? Model koji se upotrebljava za analizu regularnih izraza zove se automat s konačnim brojem stanja (engl. Finite State Automaton ili FSA) ili kraće konačni automat. Kažemo da neki konačni automat prepoznaje neki specificirani oblik ili strukturu teksta kao što je telefonski broj ili datum. Drugim riječima, konačni automati su prepoznavači. Konačni automat možemo prikazati grafom kao na slici ispod:

![automat-primjer](https://github.com/josko-heh/Evaluator/blob/main/automat-primjer.png?raw=true)<br/>

Taj se graf sastoji od čvorova koji predstavljaju stanja i lukova koji predstavljaju prelaze iz jednog stanja u drugo ako je tekući znak onaj kojim je taj luk označen. Stanje koje je označeno dvostrukom kružnicom zove se konačno stanje. Konačnih stanja može biti jedno ili više (ali mora postojati barem jedno). Gornji automat prepoznaje stringove čija je struktura opisana sljedećim regularnim izrazom: (a|b)*abb.
Ovakav prikaz automata možemo interpretirati sa sljedeći način. Neka je zadan string "baaabb". Prvi ulazni znak je "b".

- Krenemo od početnog stanja, 0. Trenutno stanje je, prema tome, 0.
- Ako postoji luk koji izlazi iz stanja 0 i označen je s "b" onda slijedimo taj luk. U suprotnom zadani string nema traženu strukturu. U ovom slučaju postoji luk označen s "b" pa ga slijedimo i ponovo se nalazimo u stanju 0. Dakle, trenutno stanje je 0.
- Sada smo obradili prvi znak pa idemo na idući, znak "a". Sada opet gledamo postoji li luk koji je označen s "a" i izlazi iz trenutnog stanja. Vidimo da postoje dva. Ako odaberemo onaj koji vodi opet u stanje 0 vraćamo se u to stanje. Dakle, trenutno stanje je i dalje 0.
- Sada smo konzumirali još jedan znak pa je sljedeći znak "a" (ovo je "a" na indeksu 2). Ako opet odaberemo luk koji vodi u stanje 0 naći ćemo se ponovo u tom stanju, a sljedeći znak je sada opet "a" na indeksu 3.
- Ako sada odaberemo luk koji za tekući znak ("a") vodi u stanje 1 naći ćemo se u stanju 1.
- Sada je idući znak "b" i trenutno stanje 1. Vidimo da postoji samo jedan izlaz iz stanja 1 za tekući znak "b" pa slijedivši taj luk idemo u stanje 2.
- U ovom trenutku ostao nam je još samo jedan znak, posljenji "b", a trenutno stanje je 2. Dakle, slijedimo luk iz stanja 2 u stanje 3 za tekući simbol "b". Stanje u kojem se sada nalazimo je 3.
- U ovom trenutku konzumirali smo sve znakove ulaznog niza (stringa), a automat se nalazi u stanju 3, to jest konačnom stanju. Ako čitanjem posljednjeg znaka automat pređe u konačno stanje onda to znači da struktura ulaznog teksta odgovara regularnom izrazu po čijoj je specifikaciji taj automat napravljen.