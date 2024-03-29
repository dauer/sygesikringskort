# Sygesikringskort

En [Groovy](http://groovy-lang.org/) klasse til indlæsning af data fra sygesikringskort som de læses med magnetkortlæser.

## Kortlæser
Bemærk at ikke alle kortlæsere læser sygesikringskort korrekt.
Der kræves en 2-spor læser som f.eks. denne fra [DanBit](http://www.danbit.dk/da/stregkode-magnetkort-rfid-pos-og-printer/11131-3-spors-magnetkortlaeser-med-usb-interface--til-sundhedskort--medlemskort-mm--med---oe-aa--sort.html)

# Data format

### Spor 1

|Beskrivelse| Antal tegn |
|:----------|-----------:|
|'%' tegn markerer start på data spor | 1 |
|Sikredes navn (efternavn og fornavn(e) adskilt af en '^')| 34 |
|Sikredes adresse | 34 |
|Sikredes bopælskommune ([BBR](https://teknik.bbr.dk/kodelister/0/1/0/Kommunekode)) | 3 |
|Sikredes postnummer | 4 |
|'?' tegn markerer afslutning af spor | 1 |

### Spor 2

|Beskrivelse| Antal tegn |
|:----------|-----------:|
|':' tegn markerer start på data spor 2 | 1 |
|Korttype | 1 |
|Nationalitetskode ([Wikipedia](https://da.wikipedia.org/wiki/ISO_3166-1) )| 3 |
|Anvendelsesområde | 1 |
|Kortudsteder | 3 |
|Personnummer | 10 |
|Sikringsgruppe |1 |
|Lægens ydernummer ([MedCom](https://www.medcom.dk/opslag/koder-tabeller-ydere/yderelokationsnumre/laegepraksis-i-danmark) )| 6 |
|Regionsnummer | 3 |
|Kommunenummer | 3 |
|Gyldig fra | 6 |
|'?' tegn markerer afslutning af spor | 1 |

Sygesikringkortet indeholder ikke danske tegn og disse skal derfor oversættes ud fra disse [tabeller](https://en.wikipedia.org/wiki/National_Replacement_Character_Set)

Her ses et eksempel på en streng som den kunne se ud:

    %DOE^JOHN JOHNNY                   ]L\KKE ALLE 7 3                   4615000?;9208100401017023451041173083461010814?

## Kilder
Formatet på data er fra Jacob Kjers indlæg på dette link:

http://dk.teknik.elektronik.narkive.com/1ppaM6u0/laesning-og-overforelse-af-data-fra-sygesikringsbevis
