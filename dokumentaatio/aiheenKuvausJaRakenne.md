# MidiSooloisti

**Aihe:** Tarkoituksenani on toteuttaa metalligenreen soveltuva soolosoittaja ohjelmallisesti käyttäen Java-kieltä.

**Toiminnot:** Tap-tempo, sävellajin valinta, kolmen tai neljän sointuasteen valinta, mahdollisuus valita nuottitiheys, soiton aloittaminen ja lopettaminen sekä MIDI-liitännän ja soundin valinta.
Ohjelma voi siis käyttää tietokoneen omaa sisäistä MIDI-soitinta ja sillä voi soittaa myös ulkoisia syntikoita, jos koneeseen on kytketty MIDI-liitäntä.

**Toiminta:** Ohjelman logiikka tuottaa kerrallaan tahdin verran nuotteja MidiNote-luokan ilmentyminä, jotka välitetään Player-luokalle listana. Tahtilajina on 4/4 ja lyhimpänä ohjelman käsittelemänä yksikkönä 1/16-nuotti. Player-luokalle toimitetaan tieto 1/16-osan etenemisestä, jolloin se joko sammuttaa edellisen nuotin ja soittaa seuraavan tai jättää nykyisen nuotin soimaan.
Uuden tahdin alkaessa luodaan uusi lista nuotteja, joihin käytetään sillä hetkellä voimassa olevia parametreja, joita voi muuttaa käyttöliittymän kautta soolotahdin aikana. Jos muutoksia ei tehdä, luodaan uuden tahdin nuotit saman sävellajin samalle sointuasteelle kuin edellisessä tahdissa.

**Ohjelman ja Midi-syntetisaattorin välinen kommunikaatio:** Sovellus käyttää Javan MidiSystem-luokan tarjoamia palveluja ja rajapintoja. getMidiDeviceInfo()-metodilla voidaan hakea MidiDevice-luokan alustamiseen tarvittavat tiedot käytettävissä olevista Midi-laitteista. Receiver-luokasta tehdyn instanssin avulla voidaan soitettavat nuotit välittää MidiDevicelle.
Ohjelmaan tehty luokka MidiNote vastaa nuottien soittamiseen ja sammuttamiseen tarvittavien ShortMessage-viestien muodostamisesta. MidiNote-luokasta muodostetut nuottioliot välitetään tahti kerrallaan Player-luokalle, joka soittaa listan siinä tahdissa, jossa sitä kehotetaan siirtymään eteenpäin 1/16-nuotin pituisissa askelissa.

**Käyttötarkoitus:** Tämä ohjelma tulee viihdekäyttöön. Sitä voi käyttää bändin kanssa, jolloin tap-tempon avulla saadaan huolehdittua siitä, että kone soittaa samassa tahdissa muun bändin kanssa. Toki voi myös huvin vuoksi laittaa levyjä soimaan ja kokeilla miltä syntikkasoolo kuulostaisi vaikkapa Led Zeppelinin kanssa.

