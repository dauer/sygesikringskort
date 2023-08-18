package dk.workbench

import dk.workbench.nrcs.Translate
import groovy.transform.CompileStatic

/**
 * En Groovy klasse til at indlæse og parse data fra danske sygesikringskort, som de læses med en magnetkortlæser
 *
 * @author Thomas Rasmussen
 */
@SuppressWarnings(['SpaceAroundMapEntryColon'])
@CompileStatic
class Sygesikringskort implements Translate {

    /**
     * Den primære metode der parser data fra sygesikringskortet
     *
     * @param str streng indeholdende data læst fra sygesikringskort, består af 116 tegn
     * @return map indeholdende de enkelte felter indeholdt i strengen fra kortet
     */
    static Map parse(final String str) {
        assert str.length() == 116

        List<String> names = str[1..34].trim().tokenize('^')
        Map<String, String> fields = [
            // --- Track 1
            firstname    : translate(names[1]),
            lastname     : translate(names[0]),
            address      : translate(str[35..68].trim()),
            // https://teknik.bbr.dk/kodelister/0/1/0/Kommunekode
            municipal    : str[69..71].trim(),
            postalcode   : str[72..75].trim(),
            // --- Track 2
            cardtype     : str[78],
            // https://da.wikipedia.org/wiki/ISO_3166-1
            nationality  : str[79..81].trim(),
            application  : str[82],
            issuer       : str[83..85].trim(),
            cpr          : str[86..95].trim(),
            group        : str[96],
            // https://www.medcom.dk/opslag/koder-tabeller-ydere/yderelokationsnumre/laegepraksis-i-danmark
            doctor       : str[97..102].trim(),
            // https://da.wikipedia.org/wiki/ISO_3166-2:DK
            region       : str[103..105].trim(),
            municipal2   : str[106..108].trim(),
            date         : str[109..114].trim(),
        ]

        return fields
    }

}
