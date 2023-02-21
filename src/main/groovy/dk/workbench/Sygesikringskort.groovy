package dk.workbench

import groovy.transform.CompileStatic

/**
 * En Groovy klasse til indlæsning af data fra sygesikringskort som de læses med magnetkortlæser.
 * @author Thomas Rasmussen
 */
@SuppressWarnings(['SpaceAroundMapEntryColon', 'UnnecessaryReturnKeyword'])
@CompileStatic
class Sygesikringskort {

    static Map parse(final String str) {
        assert str.length() == 116

        List<String> names = str[1..34].trim().tokenize('^')
        Map<String, String> fields = [
            // Track 1
            firstname    : translate(names[1]),
            lastname     : translate(names[0]),
            address      : translate(str[35..68].trim()),
            // http://bbr.dk/kommunekode/0/30
            municipal    : str[69..71].trim(),
            postalcode   : str[72..75].trim(),
            // Track 2
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

    static String translate(String str, String enc = 'da') {
        String out = ''
        // https://en.wikipedia.org/wiki/National_Replacement_Character_Set
        Map<String, List<String>> table = [
                ascii : ['#', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'],
                da    : ['#', 'Ä', 'Æ', 'Ø', 'Å', 'Ü', '_', 'ä', 'æ', 'ø', 'å', 'ü', null],
        ]
        Closure<Integer> tr = { x -> table['ascii'].findIndexOf { String c -> c == x } }
        str.each { String s ->
            out += table[enc][tr(s)] ?: s
        }

        return out
    }

}
