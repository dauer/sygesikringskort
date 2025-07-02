package dk.workbench.sygesikring

import dk.workbench.sygesikring.nrcs.Translate
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
     * @return SikredesOplysninger indeholdende de enkelte felter indeholdt i strengen fra kortet
     */
    static SikredesOplysninger parse(final String str) {
        assert str.length() == 116

        List<String> names = str[1..34].trim().tokenize('^')
        SikredesOplysninger oplysninger = new SikredesOplysninger(
                translate(names[1]),
                translate(names[0]),
                translate(str[35..68].trim()),
                str[69..71].trim(),
                str[72..75].trim(),
                str[78],
                str[79..81].trim(),
                str[82],
                str[83..85].trim(),
                str[86..95].trim(),
                str[96],
                str[97..102].trim(),
                str[103..105].trim(),
                str[106..108].trim(),
                str[109..114].trim()
        )

        return oplysninger
    }

}
