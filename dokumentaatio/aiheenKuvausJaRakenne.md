# MidiSooloisti

**Aihe:** Tarkoituksenani on toteuttaa Midi-nuotteja soittava soolosoittaja ohjelmallisesti käyttäen Java-kieltä.

**Toiminnot:** Tap-tempo, sävellajin valinta, sointuasteen valinta (I, IV tai V-aste), nuottitiheyden valinta, soiton aloittaminen ja lopettaminen sekä duuri- tai molliasteikon valinta. Ohjelma käyttää tietokoneen omaa sisäistä MIDI-soitinta ja sillä voi soittaa myös ulkoisia syntikoita, jos koneeseen on kytketty MIDI-liitäntä.

**Toiminta:** Ohjelman logiikka tuottaa kerrallaan tahdin verran nuotteja MidiNote-luokan ilmentyminä, jotka välitetään Player-luokalle listana. Tahtilajina on 4/4 ja lyhimpänä ohjelman käsittelemänä yksikkönä 1/16-nuotti. Player-luokalle toimitetaan tieto 1/16-osan etenemisestä, jolloin se joko sammuttaa edellisen nuotin ja soittaa seuraavan tai jättää nykyisen nuotin soimaan.
Uuden tahdin alkaessa luodaan uusi lista nuotteja, joihin käytetään sillä hetkellä voimassa olevia parametreja, joita voi muuttaa käyttöliittymän kautta soolotahdin aikana. Jos muutoksia ei tehdä, luodaan uuden tahdin nuotit saman sävellajin samalle sointuasteelle kuin edellisessä tahdissa.

**Ohjelman ja Midi-syntetisaattorin välinen kommunikaatio:** Sovellus käyttää Javan MidiSystem-luokan tarjoamia palveluja ja rajapintoja. getMidiDeviceInfo()-metodilla voidaan hakea MidiDevice-luokan alustamiseen tarvittavat tiedot käytettävissä olevista Midi-laitteista. Receiver-luokasta tehdyn instanssin avulla voidaan soitettavat nuotit välittää MidiDevicelle.
Ohjelmaan tehty luokka MidiNote vastaa nuottien soittamiseen ja sammuttamiseen tarvittavien ShortMessage-viestien muodostamisesta. MidiNote-luokasta muodostetut nuottioliot välitetään tahti kerrallaan Player-luokalle, joka soittaa listan siinä tahdissa, jossa sitä kehotetaan siirtymään eteenpäin 1/16-nuotin pituisissa askelissa.

**Soolon tuottamisen logiikka:** Jotta soolo kuulostaisi siltä, että sillä on jotain tekemistä kyseessä olevan sävellajin ja soinnun kanssa, täytyy sooloa tuottavalle algoritmille välittää tieto käytettävästä asteikosta. Tätä vastuuta hoitaa luokka Scale. Jos käyttöliittymästä vaihdetaan sointuastetta sävellajin pysyessä samana, välitetään soolontuottajalle käytettävä asteikko alkaen sointuastetta ilmaisevan luvun mukaisesta järjestysnumerosta. Tällä menetelmällä voidaan asteikonkäsittelyä yksinkertaistaa siten, että joka tilanteessa voidaan sointuääniksi käsittää asteikon ensimmäinen, kolmas ja viides nuotti. Scale-luokka tarjoaa asteikot ja sointuäänet ArrayList-rakenteena, joka sisältää äänenkorkeuksia vastaavat Midi-nuottikorkeudet. Tämän ansiosta asteikkoja ja sointuääniä pitkin voidaan siirtyä yksinkertaisesti listan indeksilukua muuttamalla. Pelkkä oikea asteikko ei riitä antamaan vaikutelmaa siitä, että soitetaan jonkin tietyn soinnun päälle. Tämä vaikutelma saadaan tässä tapauksessa siten, että jokaiselle neljäsosaiskulle osuvan soolonuotin on kuuluttava myös kyseessä olevaan sointuun. Erilaisia tapoja sävelkulkujen luomiseen tarjoavat Pattern-rajapinnan toteuttavat luokat joita kehitetään kuusi erilaista. Ohjelma arpoo kutakin tahtia varten käytettävän 'patternin'. Kukin Pattern-rajapintaluokan ilmentymä sisältää myös enemmän tai vähemmän satunnaisuutta, joten soolo tulee olemaan joka kerta hieman erilainen. Lisäksi satunnaisuutta hyödynnetään pidempien nuottien ilmestymisen määräämiseen. Pelkillä kuudestoistaosanuoteilla toteutettu soolo, kun olisi aika puuduttavaa kuunneltavaa muutaman tahdin jälkeen.

**Käyttötarkoitus:** Tämä ohjelma tulee viihdekäyttöön. Sitä voi käyttää bändin kanssa, jolloin tap-tempon avulla saadaan huolehdittua siitä, että kone soittaa samassa tahdissa muun bändin kanssa. Toki voi myös huvin vuoksi laittaa levyjä soimaan ja kokeilla miltä syntikkasoolo kuulostaisi vaikkapa Led Zeppelinin kanssa.

[Luokkakaavio](./luokkakaavio.pdf)


**Kahden käyttötapauksen sekvenssikaaviot:**

![Alt text](./Sekvenssikaavio1.png "Sekvenssikaavio 1")


![Alt text](./Sekvenssikaavio2.png "Sekvenssikaavio 2")


**Ohjelman rakennekuvaus**

Ohjelman käyttöliittymä tarjoaa toiminnot tempon, sävellajin, sointuasteen ja nuottitiheyden valintoihin. Lisäksi voidaan valita soitetaanko duurista vai mollista.

Midinuottien soittamiseen käytetään Javan MidiSystem-luokan tarjoamia palveluita. Ohjelman luokka Player luo MidiDevice-olion sekä sen tarvitseman Receiver-luokan. Player-luokka saa soitettavat nuotit MidiNote-oliolistana SoloLogic-luokalta. MidiNote-oliot sisältävät kaikki tarvittavat tiedot soitettavasta nuotista (korkeus, kesto, Midi-kanava ja voimakkuus) ja tarjoavat myös metodit nuottien soittamiseen ja sammuttamiseen tarvittavien ShortMessage-viestien muodostamiseen.

Sooloa generoidaan tahdin verran kerrallaan käyttöliittymän parametrien mukaan. Rajapinta Pattern määrittelee oletusmetodin getNotes(int currentNote), jonka toteuttavia luokkia on ohjelmassa kuusi erilaista. Kukin niistä tuottaa soolokuvioita eri metodeilla ja näiden kuvioiden satunnaisella vaihtelemisella saadaan sooloon riittävästi vaihtelua. Kukin Pattern-rajapinnan toteuttavista luokista sisältää myös enemmän tai vähemmän satunnaisuutta. Sooloa tuotetaan esimerkiksi soittamalla lineaarisesti asteikkoa pitkin kulkevia neljän nuotin ryhmiä, jotka liikkuvat ylös- tai alaspäin, sointuarpeggioilla tai vaikkapa Bachin Cm-preludin alkusävelillä. Parametrina annettu viimeisimpänä soitetun nuotin korkeus pitää huolta siitä, että luokat tietävät mihin säveleen edellinen tahti päättyy.

Generoitavan soolon yhteys soitettavaan sävellajiin ja sointuasteeseen syntyy siten, että soolon iskuille pyritään sijoittamaan jokin aktiivisena olevan soinnun ääni. Tässä ei jokaisen patternin tapauksessa olla aivan ehdottoman tarkkoja, koska ei musiikkia muutenkaan pidä rajoittaa liian tiukoilla säännöillä. Em. säännöstä pidetään kuitenkin sen verran kiinni, että sooloa kuunnellessaan voi samalla aistia, mikä kyseessä oleva sointu on.

Pattern-rajapinnan toteuttavat luokat hyödyntävät äänivalinnoissaan Scale-luokan tarjoamia metodeja. Scale tuntee käyttöliittymästä valitun asteikon ja pystyy kertomaan esim. annettua äänenkorkeutta lähimpänä löytyvän sointuäänen sijainnin asteikkotaulukossa, joka muodostuu ko. asteikkoon kuuluvista Midi-äänenkorkeuksista. Jos käyttöliittymästä vaihdetaan asteikkoa duurista molliin tai sävellajia, päivitetään Scale-luokan asteikkotaulukot vastaavasti. Sointuasteen muutokset näkyvät Scale-luokalle myös uutena asteikkona vaikka musiikin teorian tasolla kyse onkin vain siitä, että samaa asteikkoa käsitellään siten, että sen alku- ja loppukohtia siirretään vaikkapa neljä nuottia eteenpäin. Tällä tavoin itse soolopatternien tuottamisen logiikan ei tarvitse huomioida erikseen sitä, millä tavalla sävellajia tai sointuastetta on muutettu.

SoloLogic-luokka hallinnoi kaikkea edellä mainittua ja se tarjoaa graagista käyttöliittymää varten metodeja niin arvojen syöttämiseen kuin niiden lukemiseenkin. MidiSooloisti toimii myös ilman käyttöliittymäluokkaa. Käyttöliittymän merkitys onkin vain soolon tuottamiseen liittyvien parametrien muuttamisessa.
