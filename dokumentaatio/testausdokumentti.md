# Testausdokumentti

**Käyttöjärjestelmäluokat**

Tehtävänannon mukaisesti jätin JUnit-testit tekemättä käyttöjärjestelmäluokille. Näitä on toki testattu käsin ajamalla ohjelmaa ja tarkistamalla että asiat toimivat kuten pitääkin.
Sointuaste- ja asteikkotoimintojen testaamisessa toimivuuden toteamiseen riittää korva. Nuottitiheyden ja tempon säätöihin tein soolologiikkaluokkaan tilapäisesti koodin, joka kirjoitti tekstinä saamansa arvot, jotta pystyin toteamaan parametrien perille kulkemisen.

**Player-luokka**

Player-luokan testaaminen tuntui tähän mennessä hankittuun osaamiseen nähden liian hankalalta. Metodit kun lähinnä kommunikoivat MidiSystem-luokan kanssa. Tähän väliin ei tuntunut luontevalta laittaa JUnit-testejä ja niinpä tämänkin luokan testaaminen jäi käytännän kokeiluihin. Eräs käytännössä havaittu puute, minkä koeajot paljastivat oli sellainen, että tietyissä tilanteissa soitettu Midinuotti saattoi jäädä soimaan pitkänä samaan aikaan, kun muita soolon nuotteja soitettiin. Tässäkin asiassa oli eri syntetisaattoreiden välillä eroja. Virhe tuli kuitenkin korjattua ja toiminta on nyt yhtenäistä eri syntetisaattoreilla.

**SoloLogic-luokka**


